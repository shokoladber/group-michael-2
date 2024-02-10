package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.launchcode.caninecoach.repositories.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VerificationTokenService {

    private final VerificationTokenRepository verificationTokenRepository;
    private final UserRepository userRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository, UserRepository userRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    public VerificationToken createTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);

        // Set the expiry date for the token (e.g., 24 hours from now)
        long EXPIRY_TIME = 24 * 60 * 60 * 1000; // 24 hours in milliseconds
        Date expiryDate = new Date(System.currentTimeMillis() + EXPIRY_TIME);
        verificationToken.setExpiryDate(expiryDate);

        return verificationTokenRepository.save(verificationToken);
    }

    public boolean validateVerificationToken(String token) {
        Optional<VerificationToken> verificationTokenOpt = verificationTokenRepository.findByToken(token);

        if (verificationTokenOpt.isPresent()) {
            VerificationToken verificationToken = verificationTokenOpt.get();
            if (verificationToken.getExpiryDate().after(new Date())) {
                User user = verificationToken.getUser();
                user.setVerified(true);
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }
}
