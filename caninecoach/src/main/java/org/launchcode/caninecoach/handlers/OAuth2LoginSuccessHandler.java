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

        boolean isNewUser = userService.isNewUser(user.getEmail());
        boolean isProfileComplete = user.isProfileCreated();
        UserRole userRole = user.getRole();

        if (isNewUser) {
            return baseUrl + "/select-role";
        } else if (userRole == UserRole.TEMPORARY) {
            // User is not new but has the TEMPORARY role
            return baseUrl + "/select-role";
        } else if (!isProfileComplete) {
            // User is not new, has a role other than TEMPORARY, but profile is not complete
            return baseUrl + "/create-profile";
        } else {
            // User is not new, has a role other than TEMPORARY, and profile is complete
            return baseUrl + "/home";
        }
    }
}