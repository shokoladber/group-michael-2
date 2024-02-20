package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Endpoint to check if a user is new
    @GetMapping("/check-new-user")
    public ResponseEntity<?> checkNewUser(@AuthenticationPrincipal OAuth2User principal) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            UserDto userDto = userService.findByEmail(email);
            if (userDto != null && UserRole.TEMPORARY.equals(userDto.getRole())) {
                return ResponseEntity.ok(userDto);
            } else {
                return ResponseEntity.ok(Map.of("message", "User is not new or the role is not TEMPORARY."));
            }
        }
        return ResponseEntity.ok(Map.of("message", "No principal information available."));
    }

    // Endpoint to update the role of a new user
    @PostMapping("/select-role")
    public ResponseEntity<?> updateRole(@AuthenticationPrincipal OAuth2User principal, @RequestBody Map<String, String> request) {
        if (principal == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "No OAuth2 principal information available."));
        }

        String email = principal.getAttribute("email");
        UserDto userDto = userService.findByEmail(email);
        if (userDto == null) {
            return ResponseEntity.badRequest().body(Map.of("error", "User not found."));
        }

        String roleName = request.get("role");
        try {
            UserRole role = UserRole.valueOf(roleName);
            userService.updateUserRole(userDto.getId(), role);
            return ResponseEntity.ok(Map.of("message", "Role updated successfully."));
        } catch (IllegalArgumentException | NullPointerException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid role specified."));
        }
    }
}
