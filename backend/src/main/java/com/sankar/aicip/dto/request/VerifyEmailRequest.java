package com.sankar.aicip.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(
        name = "Verify Email Request",
        description = "Payload for verifying email address using OTP code."
)
public class VerifyEmailRequest {

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Schema(description = "User email address", example = "citizen@example.com")
    private String email;

    @NotBlank(message = "OTP code is required")
    @Pattern(regexp = "^[0-9]{6}$", message = "OTP code must be 6 digits")
    @Schema(description = "6-digit OTP code", example = "123456")
    private String otp;

    public VerifyEmailRequest() {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }
}
