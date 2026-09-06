package com.sankar.aicip.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.LocalDateTime;

@Schema(
        name = "User Profile Response",
        description = "Response containing safe profile information for the authenticated user."
)
public class UserProfileResponse {

    @Schema(description = "Unique user identifier", example = "1")
    private Long id;

    @Schema(description = "User full name", example = "Sankar P")
    private String fullName;

    @Schema(description = "Registered email address", example = "sankar@example.com")
    private String email;

    @Schema(description = "Registered phone number", example = "9876543210")
    private String phoneNumber;

    @Schema(description = "User role", example = "CITIZEN")
    private String role;

    @Schema(description = "Email verification status", example = "true")
    private boolean emailVerified;

    @Schema(description = "Phone verification status", example = "false")
    private boolean phoneVerified;

    @Schema(description = "Profile picture URL or path", example = "/uploads/avatars/user1.jpg")
    private String profilePictureUrl;

    @Schema(description = "User creation timestamp", example = "2026-08-02T10:30:45")
    private LocalDateTime createdAt;

    public UserProfileResponse() {
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

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public boolean isPhoneVerified() {
        return phoneVerified;
    }

    public void setPhoneVerified(boolean phoneVerified) {
        this.phoneVerified = phoneVerified;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public void setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
