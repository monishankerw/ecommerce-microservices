package com.ecommerce.user.controller;

import com.ecommerce.common.dto.
        ApiResponseDto;

import com.ecommerce.user.dto.*;

import com.ecommerce.user.entity.User;

import com.ecommerce.user.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ApiResponseDto<String> register(
            @RequestBody User user) {

        return ApiResponseDto.<String>builder()

                .success(true)

                .message("Registration Successful")

                .data(service.register(user))

                .build();
    }

    @PostMapping("/login")
    public ApiResponseDto<AuthResponse> login(
            @RequestBody AuthRequest request) {

        return ApiResponseDto.<AuthResponse>builder()

                .success(true)

                .message("Login Successful")

                .data(service.login(request))

                .build();
    }

    @PostMapping("/refresh")
    public ApiResponseDto<AuthResponse> refreshToken(
            @RequestBody
            RefreshTokenRequest request) {

        return ApiResponseDto.<AuthResponse>builder()

                .success(true)

                .message("Token Refreshed")

                .data(service.refreshToken(request))

                .build();
    }
}