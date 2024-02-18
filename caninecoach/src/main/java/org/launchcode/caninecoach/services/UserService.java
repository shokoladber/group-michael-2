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

    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void setVerifiedTrue(User user) {
        if (user != null) {
            user.setVerified(true);
            userRepository.save(user);
        }
    }

    public User processOAuth2User(String email, UserRole defaultRole) {
        return userRepository.findByEmail(email)
                .orElseGet(() -> createNewUser(email, defaultRole));
    }

    public boolean isNewUser(User user) {
        return !user.isProfileCreated();
    }

    public void completeUserProfile(User user) {
        user.setProfileCreated(true);
        userRepository.save(user);
    }

    private User createNewUser(String email, UserRole defaultRole) {
        User newUser = new User();
        newUser.setEmail(email);
        newUser.setRole(defaultRole);
        newUser.setUsingOAuth2(true);
        newUser.setVerified(true);
        return saveUser(newUser);
    }

    // Convert a role string to UserRole enum
    public UserRole convertStringToUserRole(String roleStr) {
        try {
            return UserRole.valueOf(roleStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Invalid role string: {}", roleStr, e);
            throw new IllegalArgumentException("Invalid role provided: " + roleStr);
        }
    }

    // Update a user's role
    public void updateUserRole(User user, String roleStr) {
        UserRole role = convertStringToUserRole(roleStr);
        user.setRole(role);
        userRepository.save(user);
    }
}
