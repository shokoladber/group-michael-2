package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.repositories.VerificationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenService {

    private static final Logger log = LoggerFactory.getLogger(VerificationTokenService.class);

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    // Generates verification token for the user
    public String createTokenForUser(User user) {
        String token = UUID.randomUUID().toString(); // Generates token?
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiryDateFromNow();
        verificationTokenRepository.save(verificationToken);
        return token;
    }


    // Validates the verification token
    public Optional<VerificationToken> validateVerificationToken(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        return verificationToken.filter(vToken -> vToken.getExpiryDate().after(new Date()));
    }


}
