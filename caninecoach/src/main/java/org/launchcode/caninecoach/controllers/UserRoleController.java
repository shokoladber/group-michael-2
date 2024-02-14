package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Collections;

@Controller
public class UserRoleController {

    private static final Logger log = LoggerFactory.getLogger(UserRoleController.class);

    private final UserRepository userRepository;

    @Autowired
    public UserRoleController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Role selection page
    @GetMapping("/select-role")
    @PreAuthorize("isAuthenticated() && @userRepository.findByEmail(#principal.getAttribute('email')).orElse(new org.launchcode.caninecoach.entities.User()).getRole() == T(org.launchcode.caninecoach.entities.UserRole).TEMPORARY")
    public String selectRole() {
        return "select-role";
    }

    // Processing and registering role selected
    @PostMapping("/update-role")
    @PreAuthorize("isAuthenticated() && @userRepository.findByEmail(#principal.getAttribute('email')).orElse(new org.launchcode.caninecoach.entities.User()).getRole() == T(org.launchcode.caninecoach.entities.UserRole).TEMPORARY")
    public ResponseEntity<?> updateRole(@RequestParam String role, @AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        User user = userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));

        try {
            UserRole selectedRole = UserRole.valueOf(role.toUpperCase());
            user.setRole(selectedRole);
            userRepository.save(user);

            // Instead of redirect, return a response indicating success
            return ResponseEntity.ok().body(Collections.singletonMap("message", "Role updated successfully"));
        } catch (IllegalArgumentException e) {
            log.error("Invalid role selection: {}", role, e);
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Invalid role selected. Please choose a valid role."));
        }
    }

}
