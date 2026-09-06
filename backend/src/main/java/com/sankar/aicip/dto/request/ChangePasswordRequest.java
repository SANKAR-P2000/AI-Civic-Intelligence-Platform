package com.sankar.aicip.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(
        name = "Change Password Request",
        description = "Payload for changing authenticated user's password."
)
public class ChangePasswordRequest {

    @NotBlank(message = "Current password is required")
    @Schema(description = "Current password", example = "OldSecret123!")
    private String currentPassword;

    @NotBlank(message = "New password is required")
    @Size(min = 8, message = "New password must be at least 8 characters long")
    @Schema(description = "New password", example = "NewSecret123!")
    private String newPassword;

    @NotBlank(message = "Confirm new password is required")
    @Schema(description = "Confirm new password", example = "NewSecret123!")
    private String confirmPassword;

    public ChangePasswordRequest() {
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
