package com.sankar.aicip.service;

import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    void deleteByUser(User user);
}