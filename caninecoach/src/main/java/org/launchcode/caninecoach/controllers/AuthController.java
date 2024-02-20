package org.launchcode.caninecoach.controllers;

import jakarta.servlet.http.HttpSession;
import org.launchcode.caninecoach.dtos.LoginDto;
import org.launchcode.caninecoach.dtos.SignupDto;
import org.launchcode.caninecoach.dtos.UserDto;
import org.launchcode.caninecoach.services.JwtTokenService;
import org.launchcode.caninecoach.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenService jwtTokenService;

    public AuthController(UserService userService, JwtTokenService jwtTokenService) {
        this.userService = userService;
        this.jwtTokenService = jwtTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody @Valid LoginDto loginDto) {
        UserDto userDto = userService.login(loginDto);
        userDto.setToken(jwtTokenService.createToken(userDto));
        return ResponseEntity.ok(userDto);
    }

    // Inside your controller class

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignupDto signupDto) {
        UserDto createdUser = userService.register(signupDto);
        // Do not set the JWT token here, as you're currently handling email verification
        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/users/{id}")
                .buildAndExpand(createdUser.getId()).toUri();
        return ResponseEntity.created(location).body(createdUser);
    }

}
