package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.security.jwt.JwtUtils;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

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


}