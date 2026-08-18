package com.sankar.aicip.service.impl;

import com.sankar.aicip.service.OtpService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("phoneOtpService")
public class PhoneOtpServiceImpl implements OtpService {

    private static final Logger logger = LoggerFactory.getLogger(PhoneOtpServiceImpl.class);

    @Override
    public void sendOtp(String target) {
        logger.info("[FUTURE READY] Generating OTP for SMS / WhatsApp verification targeting: {}", target);
        // Stub implementation for future integration with Twilio / AWS SNS / WhatsApp Business API
    }

    @Override
    public String verifyOtpAndGenerateToken(String target, String otp) {
        logger.info("[FUTURE READY] Verifying SMS / WhatsApp OTP targeting: {}", target);
        // Stub implementation
        return "stub-phone-reset-token";
    }

    @Override
    public void resendOtp(String target) {
        logger.info("[FUTURE READY] Resending SMS / WhatsApp OTP targeting: {}", target);
        // Stub implementation
    }

    @Override
    public void invalidateOtp(String target) {
        logger.info("[FUTURE READY] Invalidating SMS / WhatsApp OTP targeting: {}", target);
        // Stub implementation
    }

    @Override
    public boolean isValidResetToken(String target, String resetToken) {
        logger.info("[FUTURE READY] Checking reset token validity for SMS / WhatsApp targeting: {}", target);
        // Stub implementation
        return false;
    }
}
