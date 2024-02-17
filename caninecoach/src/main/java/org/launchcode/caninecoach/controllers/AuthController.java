package org.launchcode.caninecoach.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

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
        user.setUsingOAuth2(false);
        userService.saveUser(user);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        // Authentication logic here
        // This should check the user's email and password and return a token if successful
        return ResponseEntity.ok().build();
    }

    // OAuth2 callback endpoint
    @GetMapping("/oauth2/callback/{registrationId}")
    public ResponseEntity<?> oauth2Callback(@PathVariable String registrationId, HttpServletRequest request, HttpServletResponse response) {
        // The actual redirection to the OAuth2 provider should be handled by Spring Security's OAuth2LoginConfigurer
        // This method can be used to handle additional logic once the user is redirected back to your app after OAuth2 login
        // You might need to extract authentication details and create/update a User instance in your database here
        return ResponseEntity.ok("OAuth2 Callback Success");
    }
}
