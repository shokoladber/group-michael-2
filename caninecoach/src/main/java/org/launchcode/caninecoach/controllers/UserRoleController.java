package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class UserRoleController {

    private final UserRepository userRepository;

    @Autowired
    public UserRoleController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/select-role")
    public String selectRole() {
        return "select-role"; // Return the role selection view name
    }

    @PostMapping("/update-role")
    public String updateRole(@RequestParam String role, @AuthenticationPrincipal OAuth2User principal, HttpServletRequest request) {
        String email = principal.getAttribute("email");
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        user.setRole(UserRole.valueOf(role.toUpperCase()));
        userRepository.save(user);

        // Redirect based on the new role
        return "redirect:" + (user.getRole() == UserRole.PET_GUARDIAN ? "/create-pet-profile" : "/create-pet-trainer-profile");
    }
}

