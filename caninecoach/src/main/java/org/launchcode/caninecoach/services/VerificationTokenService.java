package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.repositories.UserRepository;
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
    private final UserRepository userRepository; // Needed to update the User's verified status

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository, UserRepository userRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
        this.userRepository = userRepository;
    }

    // Generates verification token for the user
    public String createTokenForUser(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationTokenRepository.save(verificationToken);
        return token;
    }

    // Validates the verification token and updates user's verification status
    public boolean verifyToken(String token) {
        Optional<VerificationToken> verificationTokenOpt = verificationTokenRepository.findByToken(token);
        if (verificationTokenOpt.isPresent()) {
            VerificationToken verificationToken = verificationTokenOpt.get();
            if (verificationToken.getExpiryDate().after(new Date())) { // Token is valid
                User user = verificationToken.getUser();
                user.setVerified(true); // Assuming User entity has a setVerified method
                userRepository.save(user);

                // Optionally, delete the token after successful verification to prevent reuse
                verificationTokenRepository.delete(verificationToken);

                return true;
            } else {
                // Token is expired
                log.info("Verification token is expired.");
            }
        } else {
            log.info("Invalid verification token.");
        }
        return false;
    }
}
