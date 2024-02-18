package org.launchcode.caninecoach.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.security.jwt.JwtUtils;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

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
    public ResponseEntity<?> registerUser(@RequestParam String name,
                                          @RequestParam String email,
                                          @RequestParam String password) {
        if (userService.findUserByEmail(email).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam String email,
                                              @RequestParam String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        OAuth2User userDetails = (OAuth2User) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Construct a response manually without JwtResponse DTO
        return ResponseEntity.ok(String.format("JWT Token: %s, ID: %s, Email: %s, Name: %s, Roles: %s",
                jwt, userDetails.getAttribute("id"),
                email, userDetails.getAttribute("name"),
                String.join(", ", roles)));


    }


    @GetMapping("/oauth2/redirect")
    public ResponseEntity<Void> oauth2Redirect(HttpServletRequest request, HttpServletResponse response) {
        // Assume we have a method `getCurrentUser` which retrieves the current authenticated User
        User currentUser = getCurrentUser(request);

        // Redirect new users to the user role selection page
        if (!currentUser.isProfileCreated()) {
            String roleSelectionUrl = "/role-selection";
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(roleSelectionUrl)).build();
        } else {
            // Redirect returning users to the home page
            String homeUrl = "/";
            return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(homeUrl)).build();
        }
    }

    @GetMapping("/oauth2/callback")
    public ResponseEntity<Void> oauth2Callback(HttpServletRequest request, HttpServletResponse response) {
        // Handle the OAuth2 provider's response and perform authentication
        // Assume we have a method `processOAuth2Callback` which handles the OAuth2 provider's callback
        processOAuth2Callback(request);

        // Redirect to the OAuth2 redirect endpoint which will handle further redirection logic
        String redirectUrl = "/oauth2/redirect";
        return ResponseEntity.status(HttpStatus.FOUND).location(URI.create(redirectUrl)).build();
    }

    // Helper methods (stubs for illustration purposes)
}