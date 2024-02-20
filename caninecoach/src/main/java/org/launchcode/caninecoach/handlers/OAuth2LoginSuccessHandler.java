package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.services.JwtTokenService;
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

    private final JwtTokenService jwtTokenService;
    private final UserService userService;

    @Autowired
    public OAuth2LoginSuccessHandler(JwtTokenService jwtTokenService, @Lazy UserService userService) {
        this.jwtTokenService = jwtTokenService;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        UserDto userDto = userService.processOAuth2User(email, UserRole.TEMPORARY);
        boolean isNewUser = userService.isNewUser(email);

        String token = jwtTokenService.createToken(userDto);

        // Redirecting all users to the homepage, with a special case for new users to select their role
        String redirectUrl = isNewUser ? "/select-role" : "/home";

        response.sendRedirect(redirectUrl + "?token=" + token);
    }
}
