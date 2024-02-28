package org.launchcode.caninecoach.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.handlers.AppException;
import org.launchcode.caninecoach.services.UserService;
import org.launchcode.caninecoach.services.VerificationTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;
    private final VerificationTokenService verificationTokenService;

    @Autowired
    public UserController(UserService userService, VerificationTokenService verificationTokenService) {
        this.userService = userService;
        this.verificationTokenService = verificationTokenService;
    }

    @GetMapping("/check-new-user")
    public ResponseEntity<Map<String, Object>> checkNewUser(@AuthenticationPrincipal OAuth2User principal) {
        Map<String, Object> response = new HashMap<>();
        if (principal != null) {
            String email = principal.getAttribute("email");
            UserDto userDto = userService.findByEmail(email);
            if (userDto != null && UserRole.TEMPORARY.equals(userDto.getRole())) {
                response.put("user", userDto);
            } else {
                response.put("message", "User is not new or the role is not TEMPORARY.");
            }
        } else {
            response.put("message", "No principal information available.");
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/verify-email")
    public RedirectView verifyEmail(@RequestParam("token") String token) {
        boolean verificationStatus = verificationTokenService.verifyToken(token);
        if (verificationStatus) {
            return new RedirectView("http://localhost:3000/select-role");
        } else {
            return new RedirectView("http://localhost:3000/verification-failed");
        }
    }

    @PostMapping("/select-role")
    public ResponseEntity<Map<String, String>> updateRole(@AuthenticationPrincipal OAuth2User oAuth2User, @RequestBody Map<String, String> request) {
        Map<String, String> response = new HashMap<>();
        try {
            String email = extractEmail(oAuth2User);
            UserRole newRole = UserRole.valueOf(request.get("role"));
            userService.updateUserRoleByEmail(email, newRole);
            response.put("message", "Role updated successfully for user: " + email);
            return ResponseEntity.ok(response);
        } catch (AppException ex) {
            logger.error("Error updating role: {}", ex.getMessage());
            response.put("error", ex.getMessage());
            return ResponseEntity.status(ex.getHttpStatus()).body(response);
        } catch (IllegalArgumentException e) {
            logger.error("Invalid role specified", e);
            response.put("error", "Invalid role specified.");
            return ResponseEntity.badRequest().body(response);
        }
    }

    private String extractEmail(OAuth2User oAuth2User) {
        if (oAuth2User != null) {
            return oAuth2User.getAttribute("email");
        } else {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                return authentication.getName(); // This might return "anonymousUser" for unauthenticated sessions
            } else {
                return null; // Consider how to handle this case in your application logic
            }
        }
    }
}