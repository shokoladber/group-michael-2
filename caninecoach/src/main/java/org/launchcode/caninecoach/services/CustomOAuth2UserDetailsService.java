package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(CustomOAuth2UserDetailsService.class);
    private static final SecureRandom RANDOM = new SecureRandom();

    @Autowired
    public CustomOAuth2UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);

        String email = oauthUser.getAttribute("email");
        String nameFromOAuth = oauthUser.getAttribute("name");

        // Oauth2 default name if not initially provided, keep getting error
        final String finalName = (nameFromOAuth == null || nameFromOAuth.isBlank()) ? "Default Name" : nameFromOAuth;
        final String finalPlaceholderPassword = generateRandomPlaceholderPassword();

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(finalName);
            newUser.setPassword(finalPlaceholderPassword);
            newUser.setRole(UserRole.TEMPORARY);
            newUser.setUsingOAuth2(true);
            newUser.setVerified(true);
            return userRepository.save(newUser);
        });

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().name()));
        addRoleSpecificAuthorities(user.getRole(), authorities);


        return new DefaultOAuth2User(authorities, oauthUser.getAttributes(), "email");
    }

    private void addRoleSpecificAuthorities(UserRole role, Set<GrantedAuthority> authorities) {
        // PET_GUARDIAN authorities
        if (role == UserRole.PET_GUARDIAN) {
            authorities.add(new SimpleGrantedAuthority("ACCESS_VIEW_COURSES"));
            authorities.add(new SimpleGrantedAuthority("ACCESS_ENROLL_COURSES"));
        }
        // PET_TRAINER authorities
        if (role == UserRole.PET_TRAINER) {
            authorities.addAll(Set.of(
                    new SimpleGrantedAuthority("ACCESS_VIEW_COURSES"),
                    new SimpleGrantedAuthority("ACCESS_MANAGE_COURSES"),
                    new SimpleGrantedAuthority("ACCESS_MANAGE_ANNOUNCEMENTS"),
                    new SimpleGrantedAuthority("ACCESS_ENROLL_COURSES"),
                    new SimpleGrantedAuthority("ACCESS_VIEW_ROSTER")
            ));
        }
    }

    // Password generator to complete database entry
    private String generateRandomPlaceholderPassword() {
        byte[] randomBytes = new byte[24];
        RANDOM.nextBytes(randomBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }
}
