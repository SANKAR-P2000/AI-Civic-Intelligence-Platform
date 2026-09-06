package com.sankar.aicip.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Schema(
        name = "Resend Email OTP Request",
        description = "Payload for requesting a new email verification OTP."
)
public class ResendEmailOtpRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "User email address", example = "citizen@example.com")
    private String email;

    public ResendEmailOtpRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
