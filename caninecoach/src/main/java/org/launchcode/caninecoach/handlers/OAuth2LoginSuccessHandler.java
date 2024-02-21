package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        userService.processOAuth2User(email, defaultRole);


        boolean isProfileComplete = userService.isProfileComplete(email);


        boolean isNewUser = userService.isNewUser(email);

        String baseUrl = "http://localhost:3000";
        String redirectUrl;

        if (isNewUser) {
            redirectUrl = baseUrl + "/select-role";
        } else if (!isProfileComplete) {
            redirectUrl = baseUrl + "/create-profile";
        } else {
            redirectUrl = baseUrl + "/home";
        }

        response.sendRedirect(redirectUrl);
    }
}