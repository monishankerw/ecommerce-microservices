package com.ecommerce.user.service.impl;

import com.ecommerce.user.dto.*;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.security.JwtService;
import com.ecommerce.user.service.AuthService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.
        AuthenticationManager;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.
        PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl
        implements AuthService {

    private final UserRepository repository;

    private final PasswordEncoder encoder;

    private final JwtService jwtService;

    private final AuthenticationManager
            authenticationManager;

    @Override
    public String register(User user) {

        user.setPassword(
                encoder.encode(
                        user.getPassword()
                )
        );

        repository.save(user);

        return "User Registered";
    }

    @Override
    public AuthResponse login(
            AuthRequest request) {

        authenticationManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        String accessToken =
                jwtService.generateAccessToken(
                        request.getUsername()
                );

        String refreshToken =
                jwtService.generateRefreshToken(
                        request.getUsername()
                );

        return AuthResponse.builder()

                .accessToken(accessToken)

                .refreshToken(refreshToken)

                .build();
    }

    @Override
    public AuthResponse refreshToken(
            RefreshTokenRequest request) {

        String username =
                jwtService.extractUsername(
                        request.getRefreshToken()
                );

        String newAccessToken =
                jwtService.generateAccessToken(
                        username
                );

        return AuthResponse.builder()

                .accessToken(newAccessToken)

                .refreshToken(
                        request.getRefreshToken()
                )

                .build();
    }
}