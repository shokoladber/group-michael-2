package org.launchcode.caninecoach.handlers;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    // Assuming you have a service to handle user details post-login
    // @Autowired
    // private CustomUserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws jakarta.servlet.ServletException, java.io.IOException {
        // Example logic to handle successful authentication
        // CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();

        // Perform post-login logic here, e.g., creating or updating a user record
        // userService.processOAuthPostLogin(oauthUser.getEmail());

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

