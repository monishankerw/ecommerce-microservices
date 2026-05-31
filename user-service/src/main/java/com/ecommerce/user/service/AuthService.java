package com.ecommerce.user.service;

import com.ecommerce.user.dto.*;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.security.JwtService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.
        AuthenticationManager;

import org.springframework.security.authentication.
        UsernamePasswordAuthenticationToken;

import org.springframework.security.crypto.password.
        PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authManager;

    public String register(User user) {

        user.setPassword(
                encoder.encode(
                        user.getPassword()
                )
        );

        repository.save(user);

        return "User Registered";
    }

    public String login(
            AuthRequest request) {

        authManager.authenticate(

                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        return jwtService.generateToken(
                request.getUsername()
        );
    }
}