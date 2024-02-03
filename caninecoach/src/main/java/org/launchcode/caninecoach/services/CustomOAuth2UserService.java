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

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        // Process the OAuth2 user details, for example, extracting email
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email"); // Ensure the attribute key matches your IdP's response

        // Perform database operations, e.g., find or create user
        User user = userRepository.findByEmail(email);
        if (user == null) {
            // Handle new or existing user according to your application's requirements
            // For new users, you might want to create a new User entity and store it in your database
        }

        // Define custom authorities or roles based on your needs
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        // Add more roles/authorities based on the user or other criteria

        // Create a new OAuth2User with custom authorities
        return new CustomOAuth2User(attributes, authorities, "email"); // Use a custom OAuth2User implementation if necessary
    }

    // Example CustomOAuth2User class for custom OAuth2User implementation
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
