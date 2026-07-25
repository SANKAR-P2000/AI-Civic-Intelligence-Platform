package com.sankar.aicip.service;

import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.dto.response.RefreshTokenResponse;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    void deleteByUser(User user);

    RefreshTokenResponse refreshAccessToken(String refreshToken);

    void logout(String refreshToken);
}