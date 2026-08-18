package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.*;
import com.sankar.aicip.dto.response.RefreshTokenResponse;
import com.sankar.aicip.service.OtpService;
import com.sankar.aicip.service.RefreshTokenService;
import com.sankar.aicip.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final OtpService otpService;
    private final UserService userService;

    public AuthController(
            RefreshTokenService refreshTokenService,
            @Qualifier("emailOtpService") OtpService otpService,
            UserService userService
    ) {
        this.refreshTokenService = refreshTokenService;
        this.otpService = otpService;
        this.userService = userService;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @RequestBody RefreshTokenRequest request) {
        return ResponseEntity.ok(
                refreshTokenService.refreshAccessToken(
                        request.getRefreshToken()
                )
        );
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @Valid @RequestBody LogoutRequest request) {
        refreshTokenService.logout(request.getRefreshToken());
        return ResponseEntity.ok("Logged out successfully.");
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Map<String, String>> forgotPassword(
            @Valid @RequestBody ForgotPasswordRequest request) {
        otpService.sendOtp(request.getEmail());
        return ResponseEntity.ok(Map.of("message", "If the email is registered, a verification OTP has been sent."));
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {
        String resetToken = otpService.verifyOtpAndGenerateToken(request.getEmail(), request.getOtp());
        return ResponseEntity.ok(Map.of(
                "message", "OTP verified successfully.",
                "resetToken", resetToken
        ));
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<Map<String, String>> resendOtp(
            @Valid @RequestBody ResendOtpRequest request) {
        otpService.resendOtp(request.getEmail());
        return ResponseEntity.ok(Map.of("message", "A new OTP has been sent."));
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Map<String, String>> resetPassword(
            @Valid @RequestBody ResetPasswordRequest request) {
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            return ResponseEntity.badRequest().body(Map.of("message", "Passwords do not match."));
        }
        userService.resetPassword(request.getEmail(), request.getResetToken(), request.getNewPassword());
        return ResponseEntity.ok(Map.of("message", "Password reset successful."));
    }
}