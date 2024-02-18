package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
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

    // Find a user by their email
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Save or update a user in the database
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    // Mark a user's account as verified
    public void setVerifiedTrue(User user) {
        if (user != null) {
            user.setVerified(true);
            userRepository.save(user);
        }
    }

    // Process a user after OAuth2 login/sign-up
    public User processOAuth2User(String email, UserRole defaultRole) {
        // This could be a place to update existing user details if necessary
        return userRepository.findByEmail(email)
                .orElseGet(() -> createNewUser(email, defaultRole));
    }

    // Determine if the user is considered "new" based on whether their profile has been created
    public boolean isNewUser(User user) {
        return !user.isProfileCreated();
    }

    // Complete a user's profile setup
    public void completeUserProfile(User user) {
        user.setProfileCreated(true);
        userRepository.save(user);
    }

    // Helper method to create a new User entity
    private User createNewUser(String email, UserRole defaultRole) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setRole(defaultRole);
        newUser.setUsingOAuth2(true);
        newUser.setVerified(true); // Assuming OAuth2 users are automatically verified
        // Here, profileCreated is false by default, indicating the user needs to complete their profile
        return saveUser(newUser);
    }
}
