package org.launchcode.caninecoach.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth") public class AuthController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        if (userService.findUserByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already in use.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setUsingOAuth2(false); // Assume you have this field to distinguish between OAuth2 users and others
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestParam String email, @RequestParam String password) {
        // This endpoint might not be needed if you're using Spring Security's built-in mechanisms.
        // Consider removing if not used.
        return ResponseEntity.ok().build();
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