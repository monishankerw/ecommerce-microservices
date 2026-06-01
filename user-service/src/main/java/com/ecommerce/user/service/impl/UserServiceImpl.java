package com.ecommerce.user.service.impl;

import com.ecommerce.common.dto.ApiResponseDto;
import com.ecommerce.common.dto.ChangePasswordRequest;
import com.ecommerce.common.dto.ForgotPasswordRequest;
import com.ecommerce.common.dto.ResetPasswordRequest;
import com.ecommerce.user.dto.UserProfileResponse;
import com.ecommerce.user.entity.OtpVerification;
import com.ecommerce.user.entity.User;
import com.ecommerce.user.repository.OtpRepository;
import com.ecommerce.user.repository.UserRepository;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final OtpRepository otpRepository;

    @Override
    public ApiResponseDto<UserProfileResponse> getProfile(String username) {

        User user = repository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));

        UserProfileResponse response = UserProfileResponse.builder().id(user.getId()).username(user.getUsername()).email(user.getEmail()).role(user.getRole().name()).build();

        return ApiResponseDto.<UserProfileResponse>builder().success(true).message("Profile Fetched Successfully").data(response).build();
    }

    @Override
    public ApiResponseDto<?> changePassword(String username, ChangePasswordRequest request) {
        User user = repository.findByUsername(username).orElseThrow(() -> new RuntimeException("User Not Found"));
        boolean matches = passwordEncoder.matches(request.getOldPassword(), user.getPassword());
        if (!matches) {
            throw new RuntimeException("Old Password Incorrect");
        }
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        repository.save(user);
        return ApiResponseDto.builder().success(true).message("Password Changed Successfully").data(null).build();
    }

    @Override
    public ApiResponseDto<?> forgotPassword(ForgotPasswordRequest request) {
        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("Email Not Found"));
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        OtpVerification verification = new OtpVerification();
        verification.setEmail(request.getEmail());
        verification.setOtp(otp);
        verification.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        otpRepository.save(verification);
        System.out.println("OTP = " + otp);
        return ApiResponseDto.builder().success(true).message("OTP Sent Successfully").data(null).build();
    }

    @Override
    public ApiResponseDto<?> resetPassword(ResetPasswordRequest request) {

        OtpVerification otpVerification = otpRepository.findByEmailAndOtp(request.getEmail(), request.getOtp()).orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (otpVerification.getExpiryTime().isBefore(LocalDateTime.now())) {

            throw new RuntimeException("OTP Expired");
        }

        User user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new RuntimeException("User Not Found"));

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        repository.save(user);

        otpRepository.delete(otpVerification);

        return ApiResponseDto.builder().success(true).message("Password Reset Successfully").data(null).build();
    }

    @Override
    public ApiResponseDto<?> deleteUser(Long id) {

        User user = repository.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));

        repository.delete(user);

        return ApiResponseDto.builder().success(true).message("User Deleted Successfully").data(null).build();
    }
}