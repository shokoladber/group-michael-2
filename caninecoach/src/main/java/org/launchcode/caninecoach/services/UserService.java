package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Method to mark a user as verified
    public void setVerifiedTrue(User user) {
        if (user != null) {
            user.setVerified(true); // Update the verified status to true
            userRepository.save(user); // Save the updated user entity
        }
    }
}
