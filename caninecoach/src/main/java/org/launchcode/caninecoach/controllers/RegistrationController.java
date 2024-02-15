package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String showRegistrationForm() {
        return "register"; // Name of your registration form template
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String email, @RequestParam String name, @RequestParam String password) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setPassword(passwordEncoder.encode(password));
        user.setUsingOAuth2(false); // Indicate this user is not using OAuth2 for authentication
        userService.saveUser(user);
        return "redirect:/login"; // Redirect to login page after successful registration
    }
}

