package com.sankar.aicip.service;

public interface OtpService {
    void sendOtp(String target);
    String verifyOtpAndGenerateToken(String target, String otp);
    void resendOtp(String target);
    void invalidateOtp(String target);
    boolean isValidResetToken(String target, String resetToken);
}
