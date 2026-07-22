package com.sankar.aicip.service.impl;

import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.repository.RefreshTokenRepository;
import com.sankar.aicip.service.RefreshTokenService;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private static final long REFRESH_TOKEN_VALIDITY_DAYS = 7;

    private final RefreshTokenRepository refreshTokenRepository;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public RefreshToken createRefreshToken(User user) {

        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setToken(UUID.randomUUID().toString());

        refreshToken.setExpiryDate(
                LocalDateTime.now().plusDays(REFRESH_TOKEN_VALIDITY_DAYS)
        );

        refreshToken.setRevoked(false);

        refreshToken.setUser(user);

        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refreshTokenRepository
                .findByToken(token)
                .orElseThrow(() ->
                        new RuntimeException("Refresh token not found."));

        if (refreshToken.isRevoked()) {
            throw new RuntimeException("Refresh token has been revoked.");
        }

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Refresh token has expired.");
        }

        return refreshToken;
    }

    @Override
    @Transactional
    @Modifying
    public void deleteByUser(User user) {

        refreshTokenRepository.deleteByUser(user);
    }
}