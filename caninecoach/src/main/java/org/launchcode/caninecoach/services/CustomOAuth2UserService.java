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
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Autowired
    public CustomOAuth2UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);

        String email = oauthUser.getAttribute("email");
        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRole(UserRole.TEMPORARY); // Set default role
            return userRepository.save(newUser);
        });

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name())); // Ensure ROLE_ prefix
        addRoleSpecificAuthorities(user.getRole(), authorities);

        return new DefaultOAuth2User(authorities, oauthUser.getAttributes(), "email");
    }

    private void addRoleSpecificAuthorities(UserRole role, Set<GrantedAuthority> authorities) {
        switch (role) {
            case PET_GUARDIAN:
                // PET_GUARDIAN authorities
                authorities.add(new SimpleGrantedAuthority("ACCESS_CLASS_VIEW"));
                authorities.add(new SimpleGrantedAuthority("ACCESS_HOMEWORK_VIEW"));
                authorities.add(new SimpleGrantedAuthority("ACCESS_ANNOUNCEMENTS_COMMENT"));
                break;
            case PET_TRAINER:
                // PET_TRAINER authorities
                authorities.add(new SimpleGrantedAuthority("ACCESS_CLASS_MANAGE"));
                authorities.add(new SimpleGrantedAuthority("ACCESS_HOMEWORK_MANAGE"));
                authorities.add(new SimpleGrantedAuthority("ACCESS_ANNOUNCEMENTS_POST"));
                authorities.add(new SimpleGrantedAuthority("ACCESS_ROSTERS_MANAGE"));
                break;
            default:
                log.info("Encountered an unhandled role: {}", role.name());
                break;
        }
    }
}
