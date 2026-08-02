package com.sankar.aicip.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Login Response",
        description = "Response returned after successful user authentication."
)
public class LoginResponse {
    @Schema(
            description = "Unique user identifier",
            example = "1"
    )
    private Long id;
    @Schema(
            description = "Citizen full name",
            example = "Sankar P"
    )
    private String fullName;
    @Schema(
            description = "Registered email address",
            example = "sankar@example.com"
    )
    private String email;
    @Schema(
            description = "Registered mobile number",
            example = "9876543210"
    )
    private String phoneNumber;
    @Schema(
            description = "Authenticated user role",
            example = "CITIZEN"
    )
    private String role;
    @Schema(
            description = "User login timestamp",
            example = "2026-08-02T10:45:30"
    )
    private LocalDateTime loginTime;
    @Schema(
            description = "JWT access token",
            example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    private String token;
    @Schema(
            description = "JWT refresh token",
            example = "eyJhbGciOiJIUzI1NiJ9..."
    )
    private String refreshToken;

    public LoginResponse() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(LocalDateTime loginTime) {
        this.loginTime = loginTime;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}