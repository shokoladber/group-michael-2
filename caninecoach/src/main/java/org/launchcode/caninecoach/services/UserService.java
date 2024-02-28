package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.dtos.LoginDto;
import org.launchcode.caninecoach.dtos.SignupDto;
import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.handlers.AppException;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class UserService {

    private static final Logger Log = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenService verificationTokenService;
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    public UserService(UserRepository userRepository,
                       EmailService emailService,
                       PasswordEncoder passwordEncoder,
                       VerificationTokenService verificationTokenService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
        this.passwordEncoder = passwordEncoder;
        this.verificationTokenService = verificationTokenService;
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
        user.setVerified(false);
        User savedUser = userRepository.save(user);

        String token = verificationTokenService.createTokenForUser(savedUser);

        // Send verification email
        String verificationLink = "http://localhost:3000/verify-email?token=" + token;
        try {
            emailService.sendTemplateVerificationEmail(savedUser.getEmail(), "Verify Your Email", verificationLink);
        } catch (Exception e) {
            Log.error("Error sending verification email", e);
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

    public User processOAuth2User(OAuth2User oAuth2User, UserRole defaultRole) {
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name"); // This should be the full name provided by Google

        return userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);
                    newUser.setName(name != null && !name.isBlank() ? name : "Default Name");
                    newUser.setPassword(generateRandomPlaceholderPassword());
                    newUser.setRole(defaultRole);
                    newUser.setUsingOAuth2(true);
                    newUser.setVerified(true);
                    return userRepository.save(newUser);
                });
    }

    private String generateRandomPlaceholderPassword() {
        byte[] randomBytes = new byte[24];
        RANDOM.nextBytes(randomBytes);
        return passwordEncoder.encode(Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes));
    }

    public boolean isNewUser(String email) {
        return userRepository.findByEmail(email)
                .map(User::isProfileCreated)
                .orElse(true);
    }

    public boolean isProfileComplete(String email) {
        return userRepository.findByEmail(email)
                .map(User::isProfileCreated)
                .orElse(false);
    }

    public void updateUserRole(Long userId, UserRole newRole) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException("User not found with ID: " + userId, HttpStatus.NOT_FOUND));
        user.setRole(newRole);
        userRepository.save(user);
    }

    private UserDto toUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setRole(user.getRole());
        return userDto;
    }
}
