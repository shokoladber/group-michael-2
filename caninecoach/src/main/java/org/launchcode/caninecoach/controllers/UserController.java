package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to check if the user is new and needs to complete their profile
    @GetMapping("/check-new-user")
    public ResponseEntity<?> checkNewUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findUserByEmail(email).orElse(null);
            if (user != null && userService.isNewUser(user)) {
                return ResponseEntity.ok(user);
            }
        }
        return ResponseEntity.ok("User is not new or not logged in.");
    }

    // Endpoint to update the role of a new user
    @PostMapping("/select-role")
    public ResponseEntity<?> selectRole(@AuthenticationPrincipal OAuth2User principal, @RequestParam String role) {
        String email = principal.getAttribute("email");
        User user = userService.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        userService.updateUserRole(user, role);
        return ResponseEntity.ok("Role updated successfully.");
    }

    // Endpoint to mark profile completion
    @PostMapping("/complete-profile")
    public ResponseEntity<?> completeProfile(@AuthenticationPrincipal OAuth2User principal) {
        String email = principal.getAttribute("email");
        User user = userService.findUserByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
        userService.completeUserProfile(user);
        return ResponseEntity.ok("Profile completed successfully.");
    }
}
