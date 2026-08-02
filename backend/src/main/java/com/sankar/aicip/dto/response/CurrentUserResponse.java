package com.sankar.aicip.dto.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(
        name = "Current User Response",
        description = "Response containing details of the authenticated user."
)
public class CurrentUserResponse {
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
            description = "User account creation timestamp",
            example = "2026-08-02T10:30:45"
    )
    private LocalDateTime createdAt;

    public CurrentUserResponse() {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}