package org.launchcode.caninecoach.controllers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.services.UserService;
import org.launchcode.caninecoach.services.VerificationTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
public class VerificationController {

    private static final Logger log = LoggerFactory.getLogger(VerificationController.class);

    private final VerificationTokenService verificationTokenService;
    private final UserService userService;

    @Autowired
    public VerificationController(VerificationTokenService verificationTokenService, UserService userService) {
        this.verificationTokenService = verificationTokenService;
        this.userService = userService;
    }

    @GetMapping("/verify-account")
    public ResponseEntity<String> verifyAccount(@RequestParam("token") String token) {
        Optional<VerificationToken> verificationTokenOpt = verificationTokenService.validateVerificationToken(token);
        if (verificationTokenOpt.isPresent()) {
            VerificationToken verificationToken = verificationTokenOpt.get();
            User user = verificationToken.getUser();
            userService.setVerifiedTrue(user); // Assuming UserService has this method to update the user as verified
            return ResponseEntity.ok("Account verified successfully.");
        } else {
            return ResponseEntity.badRequest().body("Invalid or expired verification token.");
        }
    }
}
