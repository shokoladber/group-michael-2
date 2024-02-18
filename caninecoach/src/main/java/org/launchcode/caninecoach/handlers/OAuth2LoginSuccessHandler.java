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
import org.launchcode.caninecoach.security.UserPrincipal;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userService.processOAuth2User(email, UserRole.TEMPORARY);
        boolean isNewUser = userService.isNewUser(user);

        // Use the fetched or created User object to create a UserPrincipal
        UserPrincipal userPrincipal = UserPrincipal.create(user);
        Authentication updatedAuthentication = new UsernamePasswordAuthenticationToken(
                userPrincipal, null, userPrincipal.getAuthorities());

        String token = jwtUtils.generateJwtToken(updatedAuthentication);

        // Determines the redirect URL for user's role
        String redirectUrl = determineTargetUrlBasedOnRole(user.getRole(), isNewUser) +
                "?token=" + token +
                "&isNewUser=" + isNewUser;

        // Redirect user
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