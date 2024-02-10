package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.repositories.VerificationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

@Service
public class VerificationTokenService {

    private static final Logger log = LoggerFactory.getLogger(VerificationTokenService.class);

    private final VerificationTokenRepository verificationTokenRepository;

    @Autowired
    public VerificationTokenService(VerificationTokenRepository verificationTokenRepository) {
        this.verificationTokenRepository = verificationTokenRepository;
    }

    public VerificationToken createTokenForUser(User user, String token) {
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setUser(user);
        verificationToken.setToken(token);
        verificationToken.setExpiryDate(calculateExpiryDate(24)); // Set the expiry date to 24 hours from now
        return verificationTokenRepository.save(verificationToken);
    }

    private Date calculateExpiryDate(int expiryTimeInHours) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.HOUR, expiryTimeInHours);
        return new Date(cal.getTimeInMillis());
    }

    public Optional<VerificationToken> validateVerificationToken(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        return verificationToken.filter(vToken -> vToken.getExpiryDate().after(new Date()));
    }

    public void setVerifiedTrue(User user) {
        user.setVerified(true);
        // Assuming UserRepository is available and autowired (or passed in some other way)
        // userRepository.save(user);
    }
}
