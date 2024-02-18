package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.security.jwt.JwtUtils;
import org.launchcode.caninecoach.services.EmailService;
import org.launchcode.caninecoach.services.UserService;
import org.launchcode.caninecoach.services.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final VerificationTokenService verificationTokenService;

    private final EmailService emailService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager, JwtUtils jwtUtils,
                          VerificationTokenService verificationTokenService, EmailService emailService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
        this.verificationTokenService = verificationTokenService;
        this.emailService = emailService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody Map<String, Object> registrationData) {
        String name = (String) registrationData.get("name");
        String email = (String) registrationData.get("email");
        String password = (String) registrationData.get("password");

        if (email == null || password == null || userService.findUserByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Invalid email or password, or email already in use.");
        }

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(passwordEncoder.encode(password));
        newUser.setRole(UserRole.TEMPORARY);
        newUser = userService.saveUser(newUser);

        String token = verificationTokenService.createTokenForUser(newUser);

        // Send verification email (customize this URL to match your front-end route)
        String verificationUrl = "http://localhost:3000/verify-account?token=" + token;
        try {
            emailService.sendTemplateVerificationEmail(newUser.getEmail(), "Verify Your Canine Coach Account", verificationUrl);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to send verification email.");
        }

        return ResponseEntity.ok("User registered successfully. Please check your email to verify your account.");
    }



    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Create a JSON response object
        Map<String, Object> response = new HashMap<>();
        response.put("token", jwt);
        response.put("email", email);
        response.put("roles", roles);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-account")
    public void verifyAccount(@RequestParam("token") String token, HttpServletResponse response) throws IOException {
        Optional<VerificationToken> verificationTokenOpt = verificationTokenService.validateVerificationToken(token);
        if (verificationTokenOpt.isPresent()) {
            VerificationToken verificationToken = verificationTokenOpt.get();
            User user = verificationToken.getUser();
            userService.setVerifiedTrue(user);

            // Redirect to the frontend role selection page
            response.sendRedirect("http://localhost:3000/select-role");
        } else {
            // Redirect to an error page
            response.sendError(HttpStatus.BAD_REQUEST.value(), "Invalid or expired verification token.");
        }
    }

}

