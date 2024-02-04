package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.forms.RegistrationForm;
import org.launchcode.caninecoach.models.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(RegistrationForm registrationForm) {
        // Assuming you handle the case where the user might already exist
        User user = new User();
        user.setEmail(registrationForm.getEmail());
        // Here you'd typically encrypt the password before setting it
        user.setPassword(passwordEncoder.encode(registrationForm.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Example of a method that could be used for OAuth2 user handling
    public User registerOrRetrieveUser(String email, String defaultPassword) {
        Optional<User> existingUser = userRepository.findByEmail(email);

        return existingUser.orElseGet(() -> {
            User newUser = new User(email, passwordEncoder.encode(defaultPassword));
            // Additional setup for the newUser object
            return userRepository.save(newUser);
        });
    }

    // Other UserService methods...
}
