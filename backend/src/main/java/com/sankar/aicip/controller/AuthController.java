package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.RefreshTokenRequest;
import com.sankar.aicip.dto.request.LogoutRequest;
import com.sankar.aicip.dto.response.RefreshTokenResponse;
import com.sankar.aicip.service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;


    public AuthController(
            RefreshTokenService refreshTokenService
    ) {

        this.refreshTokenService = refreshTokenService;
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
}