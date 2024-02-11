package org.launchcode.caninecoach.handlers;

import jakarta.servlet.ServletException;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
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

    private final UserRepository userRepository;

    @Autowired
    public OAuth2LoginSuccessHandler(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));

        // Initial target URL is set to a generic homepage or dashboard
        String targetUrl = "/home";

        // Redirect new users to a role selection page if their role is TEMPORARY or undefined
        if (user.getRole() == null || user.getRole() == UserRole.TEMPORARY) {
            targetUrl = "/select-role";
        } else if (!user.isProfileCreated()) {
            // For users who have a defined role but haven't completed their profile
            switch (user.getRole()) {
                case PET_GUARDIAN:
                    targetUrl = "/create-pet-profile";
                    break;
                case PET_TRAINER:
                    targetUrl = "/create-pet-trainer-profile";
                    break;
            }
        }

        // Use the response to redirect the user to the determined target URL
        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}
