package org.launchcode.caninecoach.handlers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.launchcode.caninecoach.entities.User;
import org.launchcode.caninecoach.entities.UserRole;
import org.launchcode.caninecoach.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

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
                                        Authentication authentication) throws IOException {
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email");

        User user = userRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found with email: " + email));

        // targetURL is homepage
        String targetUrl = getTargetUrl(user);

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    private static String getTargetUrl(User user) {
        String targetUrl = "/home";

        // New users role is default TEMPORARY until selecting guardian or trainer
        if (user.getRole() == null || user.getRole() == UserRole.TEMPORARY) {
            targetUrl = "/select-role";
        } else if (!user.isProfileCreated()) {
            // For users who have role but haven't completed their profile
            targetUrl = switch (user.getRole()) {
                case PET_GUARDIAN -> "/create-pet-profile";
                case PET_TRAINER -> "/create-pet-trainer-profile";
                default -> targetUrl;
            };
        }
        return targetUrl;
    }
}
