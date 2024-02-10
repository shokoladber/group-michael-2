package org.launchcode.caninecoach.handlers;

import jakarta.servlet.ServletException;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class OAuth2LoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));

        String targetUrl;

        if (user.isProfileCreated()) {
            targetUrl = "/home";
        } else {
            targetUrl = "/create-pet-profile";
        }

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
