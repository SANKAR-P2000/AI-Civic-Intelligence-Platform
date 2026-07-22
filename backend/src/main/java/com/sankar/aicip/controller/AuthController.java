package com.sankar.aicip.controller;

import com.sankar.aicip.dto.request.RefreshTokenRequest;
import com.sankar.aicip.dto.request.LogoutRequest;
import com.sankar.aicip.dto.response.RefreshTokenResponse;
import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.security.jwt.JwtService;
import com.sankar.aicip.service.RefreshTokenService;
import com.sankar.aicip.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    public AuthController(
            RefreshTokenService refreshTokenService,
            JwtService jwtService, UserRepository userRepository) {

        this.refreshTokenService = refreshTokenService;
        this.jwtService = jwtService;
        this.userRepository = userRepository;
    }

    @PostMapping("/refresh")
    public ResponseEntity<RefreshTokenResponse> refreshToken(
            @RequestBody RefreshTokenRequest request) {

        RefreshToken refreshToken =
                refreshTokenService.verifyRefreshToken(
                        request.getRefreshToken());
        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(refreshToken.getUser().getEmail())
                        .password(refreshToken.getUser().getPassword())
                        .roles(refreshToken.getUser().getRole().name())
                        .build();

        String accessToken =
                jwtService.generateToken(userDetails);

        RefreshTokenResponse response =
                new RefreshTokenResponse();

        response.setAccessToken(accessToken);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(
            @RequestBody LogoutRequest request) {
        System.out.println("========== LOGOUT API CALLED ==========");
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("User not found."));

        refreshTokenService.deleteByUser(user);

        return ResponseEntity.ok("Logout successful.");
    }
}