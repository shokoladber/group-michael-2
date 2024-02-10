package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    private static final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);
        String email = oauthUser.getAttribute("email");
        String name = oauthUser.getAttribute("name");

        // Check if user exists
        User user = userRepository.findByEmail(email).orElse(null);

        if (user == null) {
            // New user
            user = new User();
            user.setEmail(email);
            user.setName(name);
            // Set any default roles or attributes here
            userRepository.save(user);
            log.info("New user registered through OAuth2: {}", email);
        } else {
            // Existing user, update details if necessary
            user.setName(name); // Example: Update name
            userRepository.save(user);
            log.info("Returning user logged in through OAuth2: {}", email);
        }

        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                oauthUser.getAttributes(), "email");
    }
}

