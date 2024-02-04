package org.launchcode.caninecoach.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.launchcode.caninecoach.repositories.UserRepository;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/profile")
    public String userProfile(OAuth2AuthenticationToken authentication, Model model) {
        if (authentication != null) {
            Map<String, Object> attributes = authentication.getPrincipal().getAttributes();
            String email = (String) attributes.get("email");
            var user = userRepository.findByEmail(email);
            model.addAttribute("user", user);
        }
        return "profile";
    }
}
