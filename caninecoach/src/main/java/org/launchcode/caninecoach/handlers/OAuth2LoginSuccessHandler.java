package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.services.TokenService;
import org.launchcode.caninecoach.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final TokenService tokenService;
    private final UserService userService;

    @Autowired
    public OAuth2LoginSuccessHandler(TokenService tokenService, UserService userService) {
        this.tokenService = tokenService;
        this.userService = userService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        UserDto userDto = userService.processOAuth2User(email, UserRole.TEMPORARY);
        boolean isNewUser = userService.isNewUser(email);

        String token = tokenService.createToken(userDto);

        String redirectUrl; // Default redirect, adjust based on your logic
        if (isNewUser) {
            redirectUrl = "/select-role";
        } else {
            redirectUrl = switch (userDto.getRole()) {
                case PET_GUARDIAN -> "/profile/pet-guardian";
                case PET_TRAINER -> "/profile/pet-trainer";
                default -> "/home";
            };
        }

        response.sendRedirect(redirectUrl + "?token=" + token);
    }
}
