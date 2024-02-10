package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public VerificationToken createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        // Optionally set the expiry date for the token
        return verificationTokenRepository.save(verificationToken);
    }

    public Optional<VerificationToken> getToken(String token) {
        return verificationTokenRepository.findByToken(token);
    }

    // Additional methods as needed...
}
