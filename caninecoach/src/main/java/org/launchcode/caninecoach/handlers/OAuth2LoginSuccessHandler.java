package org.launchcode.caninecoach.handlers;

import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final Logger logger = LoggerFactory.getLogger(OAuth2LoginSuccessHandler.class);
    private final UserService userService;

    @Autowired
    public OAuth2LoginSuccessHandler(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        if (email == null) {
            logger.error("OAuth2User does not have an email attribute.");
            response.sendRedirect("/error?message=OAuth2User has no email");
            return;
        }

        User user = userService.findUserByEmail(email)
                .orElseThrow(() -> new IllegalStateException("User with email " + email + " not found after OAuth2 login"));

        // Determine the redirect URL based on the user's role
        String redirectUrl = determineRedirectUrl(user);
        response.sendRedirect(redirectUrl);
    }

    private String determineRedirectUrl(User user) {
        String baseUrl = "http://localhost:3000"; // Development URL, consider using environment variables or application properties for production environments

        return switch (user.getRole()) {
            case TEMPORARY -> baseUrl + "/select-role";
            case PET_GUARDIAN -> baseUrl + "/pet-profile";
            case PET_TRAINER -> baseUrl + "/trainer-profile";
            default -> baseUrl + "/home";
        };
    }
}
