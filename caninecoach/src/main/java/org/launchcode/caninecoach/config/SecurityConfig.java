package org.launchcode.caninecoach.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.launchcode.caninecoach.handlers.OAuth2LoginSuccessHandler;
import org.launchcode.caninecoach.services.CustomOAuth2UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private static final Logger log = LoggerFactory.getLogger(SecurityConfig.class);

    private final CustomOAuth2UserService customOAuth2UserService;
    private final OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;

    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService, OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler) {
        this.customOAuth2UserService = customOAuth2UserService;
        this.oAuth2LoginSuccessHandler = oAuth2LoginSuccessHandler;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/oauth2/code/google").permitAll()
                        .requestMatchers("/", "/home", "/login", "/oauth2/**", "/register", "/logout-success").permitAll() // Include register and logout-success in the permitAll list
                        .requestMatchers("/api/googleclassroom/courses/manage/**").hasAuthority("ACCESS_MANAGE_COURSES")
                        .requestMatchers("/api/googleclassroom/courses/enroll/**").hasAuthority("ACCESS_ENROLL_COURSES")
                        .requestMatchers("/api/googleclassroom/courses/view/**").hasAuthority("ACCESS_VIEW_COURSES")
                        .requestMatchers("/api/googleclassroom/courses/announcements/**").hasAuthority("ACCESS_MANAGE_ANNOUNCEMENTS")
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2 -> oauth2
                        .userInfoEndpoint(userInfo -> userInfo
                                .userService(customOAuth2UserService) // Custom OAuth2 User Service
                        )
                        .successHandler(oAuth2LoginSuccessHandler)
                        .redirectionEndpoint(redirection -> redirection
                                .baseUri("/oauth2/redirect") // This should match your redirect URI
                        )
                        // Optional: If you want to customize the authorization request base URI
                        .authorizationEndpoint(authorization -> authorization
                                .baseUri("/oauth2/authorization")
                        )
                )

                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/home", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/logout-success")
                        .permitAll()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
