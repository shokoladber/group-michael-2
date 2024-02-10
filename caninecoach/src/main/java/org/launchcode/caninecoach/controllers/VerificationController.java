package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.services.VerificationTokenService;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {

    private final VerificationTokenService verificationTokenService;
    private final UserService userService;

    @Autowired
    public VerificationController(VerificationTokenService verificationTokenService, UserService userService) {
        this.verificationTokenService = verificationTokenService;
        this.userService = userService;
    }

    @GetMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        return verificationTokenService.validateVerificationToken(token)
                ? ResponseEntity.ok("Account verified successfully.")
                : ResponseEntity.badRequest().body("Invalid or expired verification token.");
    }
}
