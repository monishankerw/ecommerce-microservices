package com.ecommerce.user.service;

import com.ecommerce.user.dto.*;
import com.ecommerce.user.entity.User;

public interface AuthService {

    String register(User user);

    AuthResponse login(
            AuthRequest request
    );

    AuthResponse refreshToken(
            RefreshTokenRequest request
    );
}