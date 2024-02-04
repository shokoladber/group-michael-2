package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.forms.RegistrationForm;
import org.launchcode.caninecoach.services.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import jakarta.validation.Valid;

@Controller
public class RegistrationController {

    private final UserDetailsService userDetailsService;

    @Autowired
    public RegistrationController(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegistrationForm registrationForm,
                                          BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        userDetailsService.registerUser(registrationForm);
        return "redirect:/login";
    }
}
