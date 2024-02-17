package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.dtos.LoginRequest;
import org.launchcode.caninecoach.dtos.SignUpRequest;
import org.launchcode.caninecoach.dtos.JwtResponse;
import org.launchcode.caninecoach.security.UserPrincipal;
import org.launchcode.caninecoach.security.jwt.JwtUtils;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; // Spring Security authentication
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;


import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager; // Make sure this is configured in your Spring Security config
    private final JwtUtils jwtUtils; // Make sure you have this utility class for JWT handling

    @Autowired
    public AuthController(UserService userService,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtUtils jwtUtils) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        if (userService.findUserByEmail(signUpRequest.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }
        User user = new User();
        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        // Assume you set roles when saving the user or have a default role set in the User entity
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Assuming UserPrincipal is your UserDetails implementation
        UserPrincipal userDetails = (UserPrincipal) authentication.getPrincipal();

        // Now you can safely call getAuthorities()
        var roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                jwtUtils.generateJwtToken(authentication),
                userDetails.getId(),
                userDetails.getEmail(),
                userDetails.getName(),
                roles));
    }

    @GetMapping("/oauth2/redirect")
    public ResponseEntity<?> oauth2Redirect(HttpServletRequest request, HttpServletResponse response) {
        // Handle logic after successful OAuth2 authentication, such as redirection based on roles or setting up first-time login information.
        return ResponseEntity.ok().build();
    }

    @GetMapping("/oauth2/callback")
    public ResponseEntity<?> oauth2Callback(HttpServletRequest request, HttpServletResponse response) {
        // Handle logic after successful OAuth2 authentication, such as redirection based on roles or setting up first-time login information.
        return ResponseEntity.ok().build();
    }

}

