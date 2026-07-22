package com.sankar.aicip.dto.response;

public class RefreshTokenResponse {

    private String accessToken;

    public RefreshTokenResponse() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}