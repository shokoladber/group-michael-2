package org.launchcode.caninecoach.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

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
