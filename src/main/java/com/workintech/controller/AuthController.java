package com.workintech.controller;

import com.workintech.dto.LoginRequest;
import com.workintech.dto.RegisterRequest;
import com.workintech.entity.User;
import com.workintech.service.AuthenticationService;
import com.workintech.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationService authenticationService;
    private final UserService userService;

    public AuthController(AuthenticationService authenticationService, UserService userService) {
        this.authenticationService = authenticationService;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest registerRequest) {
        authenticationService.register(registerRequest.fullName(), registerRequest.email(), registerRequest.password());
        return registerRequest.fullName();
    }

    @PostMapping("/login")
    public String Login(@RequestBody LoginRequest loginRequest) {
        authenticationService.login(loginRequest.email(), loginRequest.password());
        return userService.loadUserByUsername(loginRequest.email()).getFullName();
    }
}
