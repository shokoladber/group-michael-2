package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole; // Ensure this is the correct import for UserRole
import org.launchcode.caninecoach.repositories.UserRepository;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final UserService userService;

    @Autowired
    public OAuth2AuthSuccessHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userService.processOAuth2User(email, UserRole.TEMPORARY);

        String targetUrl = determineTargetUrlBasedOnRole(user.getRole());
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private String determineTargetUrlBasedOnRole(UserRole role) {
        if (role == UserRole.TEMPORARY) {
            return "/select-role";
        } else {
            return "/home";
        }
    }
}