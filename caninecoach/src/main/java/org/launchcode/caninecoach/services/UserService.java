package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.dtos.LoginDto;
import org.launchcode.caninecoach.dtos.SignupDto;
import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.entities.VerificationToken;
import org.launchcode.caninecoach.exceptions.AppException;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.launchcode.caninecoach.repositories.VerificationTokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class UserService {

    private static final Logger Log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       VerificationTokenRepository verificationTokenRepository,
                       EmailService emailService,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.verificationTokenRepository = verificationTokenRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
    }

    public UserDto login(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail())
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));

        if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
            throw new AppException("Invalid password", HttpStatus.UNAUTHORIZED);
        }

        return toUserDto(user);
    }

    public UserDto register(SignupDto signupDto) {
        if (userRepository.existsByEmail(signupDto.getEmail())) {
            throw new AppException("Email already exists", HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setName(signupDto.getName());
        user.setEmail(signupDto.getEmail());
        user.setPassword(passwordEncoder.encode(signupDto.getPassword()));
        user.setRole(UserRole.TEMPORARY);
        User savedUser = userRepository.save(user);

        // Generate and save verification token
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token, savedUser);
        verificationTokenRepository.save(verificationToken);

        // Send verification email
        String verificationLink = "http://localhost:3000/verify?token=" + token; // Adjust the link as per your frontend route
        try {
            emailService.sendTemplateVerificationEmail(user.getEmail(), "Verify your email", verificationLink);
        } catch (Exception e) {
            Log.error("Error sending verification email", e);
            // Optionally handle this more gracefully
        }

        return toUserDto(savedUser);
    }

    public UserDto save(User user) {
        User savedUser = userRepository.save(user);
        return toUserDto(savedUser);
    }


    public UserDto findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
        return toUserDto(user);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new AppException("User not found", HttpStatus.NOT_FOUND));
    }

    public UserDto processOAuth2User(String email, UserRole defaultRole) {
        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setRole(defaultRole);
                    newUser.setUsingOAuth2(true);
                    newUser.setVerified(true); // Automatically verify OAuth2 users
                    return userRepository.save(newUser);
                });

        return toUserDto(user);
    }

    public boolean isNewUser(String email) {
        return userRepository.findByEmail(email)
                .map(User::isProfileCreated)
                .orElse(true);
    }

    public void updateUserRole(Long userId, UserRole newRole) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException("User not found with ID: " + userId, HttpStatus.NOT_FOUND));
        user.setRole(newRole);
        userRepository.save(user);
    }

    private UserDto toUserDto(User user) {
        return new UserDto(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole(),
                user.getPassword(),
                null // Token is managed elsewhere
        );
    }
}
