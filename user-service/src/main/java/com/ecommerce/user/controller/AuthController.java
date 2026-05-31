package com.ecommerce.user.controller;

import com.ecommerce.user.dto.*;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService service;

    @PostMapping("/register")
    public String register(
            @RequestBody User user) {

        return service.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody AuthRequest request) {

        String token =
                service.login(request);

        return new AuthResponse(token);
    }
}