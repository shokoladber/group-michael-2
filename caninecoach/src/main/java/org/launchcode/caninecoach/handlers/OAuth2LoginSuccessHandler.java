package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

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

        UserRole defaultRole = UserRole.TEMPORARY;
        User user = userService.processOAuth2User(email, defaultRole);


        if (user == null) {
            throw new IllegalStateException("User cannot be null after processing OAuth2 user");
        }

        String redirectUrl = determineRedirectUrl(user);

        response.sendRedirect(redirectUrl);
    }

    private String determineRedirectUrl(User user) {
        String baseUrl = "http://localhost:3000";


        if (userService.isNewUser(user.getEmail()) || user.getRole() == UserRole.TEMPORARY) {
            return baseUrl + "/api/select-role";
        } else {

            return switch (user.getRole()) {
                case PET_GUARDIAN -> baseUrl + "/pet-profiles";
                case PET_TRAINER -> baseUrl + "/trainer-profiles";
                default -> baseUrl + "/home";
            };
        }
    }

}
