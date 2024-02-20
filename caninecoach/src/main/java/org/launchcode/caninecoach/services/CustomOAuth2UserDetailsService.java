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
import java.util.HashSet;
import java.util.Set;

@Service
public class CustomOAuth2UserDetailsService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(CustomOAuth2UserDetailsService.class);

    @Autowired
    public CustomOAuth2UserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        User user = userRepository.findByEmail(email).map(existingUser -> {
            // For existing users, consider not changing the role or dynamically deciding based on certain conditions
            log.info("Existing user {} logged in via OAuth2", existingUser.getEmail());
            return existingUser;
        }).orElseGet(() -> {
            // Only new users are assigned TEMPORARY role
            log.info("Registering new user via OAuth2 with email {}", email);
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setName(name != null ? name : "Default Name");
            newUser.setRole(UserRole.TEMPORARY);
            return userRepository.save(newUser);
        });

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        // Optionally, add role-specific authorities here

        return new DefaultOAuth2User(authorities, oauthUser.getAttributes(), "email");
    }
}
