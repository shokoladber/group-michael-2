package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.security.jwt.JwtUtils;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils; // Use JwtUtils here instead of JwtTokenProvider

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userService.processOAuth2User(email, UserRole.TEMPORARY);
        boolean isNewUser = userService.isNewUser(user);

        // Generate the JWT token using JwtUtils
        String token = jwtUtils.generateJwtToken(authentication);

        // Adjusted to pass isNewUser as a second argument to determineTargetUrlBasedOnRole
        String redirectUrl = determineTargetUrlBasedOnRole(user.getRole(), isNewUser) + "?token=" + token;

        // Redirect the user
        response.sendRedirect(redirectUrl);
    }

    private String determineTargetUrlBasedOnRole(UserRole role, boolean isNewUser) {
        if (isNewUser && role == UserRole.TEMPORARY) {
            return "/select-role";
        }
        return switch (role) {
            case PET_GUARDIAN -> "/profile/pet-guardian";
            case PET_TRAINER -> "/profile/pet-trainer";
            default -> "/home";
        };
    }
}
