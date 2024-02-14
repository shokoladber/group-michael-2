package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole; // Ensure this is the correct import for UserRole
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserRepository userRepository;

    @Autowired
    public OAuth2LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email).orElseGet(() -> {
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setRole(UserRole.TEMPORARY);
            return userRepository.save(newUser);
        });

        // Determine the redirect URL based on the user's role
        String targetUrl = determineTargetUrlBasedOnRole(user.getRole());

        // Redirect to the frontend route
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrlBasedOnRole(UserRole role) {

        if (role == UserRole.TEMPORARY) {
            return "/select-role"; // role selection if new
        } else {
            // For other roles, redirect to the home page or a dashboard as appropriate
            return "/home";
        }
    }
}
