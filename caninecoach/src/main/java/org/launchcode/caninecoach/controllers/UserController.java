package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Display the registration or profile completion page for new users
    @GetMapping("/new-user")
    public String newUser(@AuthenticationPrincipal OAuth2User principal, Model model) {
        if (principal != null) {
            String email = principal.getAttribute("email");
            User user = userService.findUserByEmail(email).orElse(null);
            if (user != null) {
                model.addAttribute("user", user);
                // Check if the user is new and needs to select a role or complete their profile
                if (userService.isNewUser(user)) {
                    return "user/new-user"; // Return the view for new user onboarding
                }
            }
        }
        return "redirect:/home"; // Redirect to the home page if the user is not new or not logged in
    }

    // Handle role selection for new users
    @PostMapping("/select-role")
    public String selectRole(User user, String role) {
        userService.updateUserRole(user, role); // Implement this method in UserService to update the user's role
        return "redirect:/profile-completion"; // Redirect to the profile completion page
    }

    // Display profile completion page
    @GetMapping("/profile-completion")
    public String profileCompletion(@AuthenticationPrincipal OAuth2User principal, Model model) {
        String email = principal.getAttribute("email");
        User user = userService.findUserByEmail(email).orElse(null);
        if (user != null) {
            model.addAttribute("user", user);
            return "user/profile-completion"; // Return the view for completing the profile
        }
        return "redirect:/home"; // Redirect to the home page if the user is not found
    }

    // Handle profile completion form submission
    @PostMapping("/complete-profile")
    public String completeProfile(User user) {
        userService.completeUserProfile(user); // Implement this method in UserService to mark the profile as completed
        return "redirect:/home"; // Redirect to the home page after profile completion
    }
}
