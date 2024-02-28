package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
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

    private final Logger logger = LoggerFactory.getLogger(CustomOAuth2UserDetailsService.class);
    private final UserService userService;

    @Autowired
    public CustomOAuth2UserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauthUser = super.loadUser(userRequest);


        User user = userService.processOAuth2User(oauthUser, UserRole.TEMPORARY);

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
}
