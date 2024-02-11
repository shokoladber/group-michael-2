package org.launchcode.caninecoach.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @GetMapping("/login")
    public String displayLoginForm() {
        //template here?
        return "login";
    }

    @GetMapping("/logout-success")
    public String displayLogoutPage() {
        // template here?
        return "logout";
    }

}
