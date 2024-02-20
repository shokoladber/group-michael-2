package org.launchcode.caninecoach.controllers;

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

    @PostMapping("/signup")
    public ResponseEntity<UserDto> register(@RequestBody @Valid SignupDto user) {
        UserDto createdUser = userService.register(user);
        createdUser.setToken(jwtTokenService.createToken(createdUser));
        return ResponseEntity.created(URI.create("/users/" + createdUser.getId())).body(createdUser);
    }

}
