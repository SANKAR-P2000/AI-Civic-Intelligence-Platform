package com.sankar.aicip.service.impl;

import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.repository.RefreshTokenRepository;
import com.sankar.aicip.service.RefreshTokenService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.sankar.aicip.dto.response.RefreshTokenResponse;
import com.sankar.aicip.security.jwt.JwtService;
import org.springframework.security.core.userdetails.UserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private static final Logger logger =
            LoggerFactory.getLogger(RefreshTokenServiceImpl.class);

    private static final long REFRESH_TOKEN_VALIDITY_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtService jwtService;

    public RefreshTokenServiceImpl(
            RefreshTokenRepository refreshTokenRepository,
            JwtService jwtService) {

        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtService = jwtService;
    }


    @Override
    public RefreshToken createRefreshToken(User user) {
        logger.info("Creating refresh token for user: {}",
                user.getEmail());

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusDays(REFRESH_TOKEN_VALIDITY_DAYS)
        );

        refreshToken.setRevoked(false);

        refreshToken.setUser(user);

        RefreshToken savedRefreshToken =
                refreshTokenRepository.save(refreshToken);

        logger.info("Refresh token created successfully for user: {}",
                user.getEmail());

        return savedRefreshToken;
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {
        logger.info("Verifying refresh token.");

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Refresh token not found."));

        if (refreshToken.isRevoked()) {
            throw new RuntimeException("Refresh token has been revoked.");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token has expired.");
        }
        logger.info("Refresh token verified successfully.");
        return refreshToken;
    }

    @Override
    @Transactional
    public void deleteByUser(User user) {
        refreshTokenRepository.deleteByUser(user);
    }

    @Override
    public RefreshTokenResponse refreshAccessToken(String refreshTokenValue) {
        logger.info("Refreshing access token.");

        RefreshToken refreshToken =
                verifyRefreshToken(refreshTokenValue);

        User user = refreshToken.getUser();

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build();

        String accessToken =
                jwtService.generateToken(userDetails);
        logger.info("Access token refreshed successfully for user: {}",
                user.getEmail());

        return new RefreshTokenResponse(
                accessToken,
                refreshToken.getToken()
        );
    }

    @Override
    @Transactional
    public void logout(String refreshTokenValue) {
        logger.info("Logout requested.");

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(refreshTokenValue)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Refresh token not found."));
        logger.info("Deleting refresh token.");
        refreshTokenRepository.delete(refreshToken);
        logger.info("User logged out successfully.");
    }
}