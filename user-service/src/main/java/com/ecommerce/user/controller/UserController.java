package com.ecommerce.user.controller;

import com.ecommerce.common.dto.ApiResponseDto;
import com.ecommerce.common.dto.ChangePasswordRequest;
import com.ecommerce.common.dto.ForgotPasswordRequest;
import com.ecommerce.common.dto.ResetPasswordRequest;
import com.ecommerce.user.dto.UserProfileResponse;
import com.ecommerce.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService service;

    @GetMapping("/active")
    public String users() {
        return "User Service Running";
    }


    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(@RequestHeader("X-User") String username) {
        log.info("USERNAME = " + username);
        return ResponseEntity.ok(service.getProfile(username));
    }

    @PutMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestHeader("X-User") String username, @RequestBody ChangePasswordRequest request) {
        return ResponseEntity.ok(service.changePassword(username, request));
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody ForgotPasswordRequest request) {
        return ResponseEntity.ok(service.forgotPassword(request));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest request) {
        return ResponseEntity.ok(service.resetPassword(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.deleteUser(id));
    }
}