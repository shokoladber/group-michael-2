package org.launchcode.caninecoach.services;

import org.launchcode.caninecoach.models.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);


        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");


        User user = userRepository.findByEmail(email)
                .orElseGet(() -> {
                    User newUser = new User();
                    newUser.setEmail(email);

                    return userRepository.save(newUser);
                });


        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));


        return new CustomOAuth2User(attributes, authorities, "email");
    }

    public static class CustomOAuth2User implements OAuth2User {
        private Map<String, Object> attributes;
        private Set<SimpleGrantedAuthority> authorities;
        private String nameAttributeKey;

        public CustomOAuth2User(Map<String, Object> attributes, Set<SimpleGrantedAuthority> authorities, String nameAttributeKey) {
            this.attributes = attributes;
            this.authorities = authorities;
            this.nameAttributeKey = nameAttributeKey;
        }

        @Override
        public Map<String, Object> getAttributes() {
            return attributes;
        }

        @Override
        public Collection<SimpleGrantedAuthority> getAuthorities() {
            return authorities;
        }

        @Override
        public String getName() {
            return (String) attributes.get(nameAttributeKey);
        }
    }
}
