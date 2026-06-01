package com.ecommerce.user.service;

import com.ecommerce.common.dto.ApiResponseDto;
import com.ecommerce.common.dto.ChangePasswordRequest;
import com.ecommerce.common.dto.ForgotPasswordRequest;
import com.ecommerce.common.dto.ResetPasswordRequest;
import com.ecommerce.user.dto.UserProfileResponse;

public interface UserService {
    public ApiResponseDto<UserProfileResponse> getProfile(String username);

    public ApiResponseDto<?> changePassword(String username, ChangePasswordRequest request);

    public ApiResponseDto<?> forgotPassword(ForgotPasswordRequest request);

    public ApiResponseDto<?> resetPassword(ResetPasswordRequest request);

    Object deleteUser(Long id);
}