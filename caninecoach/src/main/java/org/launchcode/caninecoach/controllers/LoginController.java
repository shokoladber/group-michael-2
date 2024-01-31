package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.forms.LoginForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute("loginForm", new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute LoginForm loginForm) {
        // Authentication is handled by Spring Security
        return "redirect:/home"; // Redirect after successful login
    }
}
