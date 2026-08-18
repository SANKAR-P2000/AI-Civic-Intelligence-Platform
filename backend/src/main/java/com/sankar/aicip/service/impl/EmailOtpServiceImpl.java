package com.sankar.aicip.service.impl;

import com.sankar.aicip.entity.OtpVerification;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.OtpType;
import com.sankar.aicip.exception.BadRequestException;
import com.sankar.aicip.repository.OtpVerificationRepository;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.service.OtpService;
import com.sankar.aicip.service.email.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service("emailOtpService")
public class EmailOtpServiceImpl implements OtpService {

    private static final Logger logger = LoggerFactory.getLogger(EmailOtpServiceImpl.class);
    private static final int MAX_ATTEMPTS = 5;
    private static final int OTP_EXPIRY_MINUTES = 10;
    private static final int COOLDOWN_SECONDS = 60;

    private final OtpVerificationRepository otpVerificationRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;
    private final SecureRandom secureRandom = new SecureRandom();

    public EmailOtpServiceImpl(
            OtpVerificationRepository otpVerificationRepository,
            UserRepository userRepository,
            EmailService emailService) {
        this.otpVerificationRepository = otpVerificationRepository;
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Override
    @Transactional
    public void sendOtp(String target) {
        logger.info("Initiating OTP generation for email: {}", target);
        
        // Prevent email enumeration
        Optional<User> userOpt = userRepository.findByEmail(target);
        if (userOpt.isEmpty()) {
            logger.warn("Password reset requested for non-existent email address: {}", target);
            return;
        }

        // Clean up previous OTPs for this target
        otpVerificationRepository.deleteByTarget(target);

        String otp = generateOtpCode();
        String otpHash = hashOtp(otp);

        OtpVerification verification = new OtpVerification();
        verification.setTarget(target);
        verification.setOtpHash(otpHash);
        verification.setExpiryTime(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        verification.setAttempts(0);
        verification.setVerified(false);
        verification.setType(OtpType.EMAIL);
        verification.setLastSentAt(LocalDateTime.now());

        otpVerificationRepository.save(verification);

        emailService.sendOtpEmail(target, otp);
    }

    @Override
    @Transactional
    public String verifyOtpAndGenerateToken(String target, String otp) {
        logger.info("Verifying OTP for email: {}", target);

        // Fetch latest OTP verification
        OtpVerification verification = otpVerificationRepository
                .findFirstByTargetOrderByCreatedAtDesc(target)
                .orElseThrow(() -> new BadRequestException("Invalid request. Please request a new OTP."));

        if (verification.isVerified()) {
            throw new BadRequestException("OTP has already been verified.");
        }

        if (verification.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new BadRequestException("OTP has expired.");
        }

        if (verification.getAttempts() >= MAX_ATTEMPTS) {
            otpVerificationRepository.delete(verification);
            throw new BadRequestException("Too many failed attempts. Please request a new OTP.");
        }

        String inputHash = hashOtp(otp);
        if (!verification.getOtpHash().equals(inputHash)) {
            verification.setAttempts(verification.getAttempts() + 1);
            otpVerificationRepository.save(verification);
            throw new BadRequestException("Invalid OTP code.");
        }

        // Generate temporary reset token
        String resetToken = UUID.randomUUID().toString();
        verification.setVerified(true);
        verification.setResetToken(resetToken);
        // Reset token is valid for another 10 minutes
        verification.setExpiryTime(LocalDateTime.now().plusMinutes(10));
        otpVerificationRepository.save(verification);

        logger.info("OTP verified successfully. Reset token generated for email: {}", target);
        return resetToken;
    }

    @Override
    @Transactional
    public void resendOtp(String target) {
        logger.info("Resending OTP for email: {}", target);

        Optional<User> userOpt = userRepository.findByEmail(target);
        if (userOpt.isEmpty()) {
            // Silently return to prevent enumeration
            return;
        }

        OtpVerification verification = otpVerificationRepository
                .findFirstByTargetOrderByCreatedAtDesc(target)
                .orElseThrow(() -> new BadRequestException("No active OTP request found."));

        if (verification.getLastSentAt().plusSeconds(COOLDOWN_SECONDS).isAfter(LocalDateTime.now())) {
            throw new BadRequestException("Please wait at least 1 minute before requesting a new OTP.");
        }

        String otp = generateOtpCode();
        String otpHash = hashOtp(otp);

        verification.setOtpHash(otpHash);
        verification.setExpiryTime(LocalDateTime.now().plusMinutes(OTP_EXPIRY_MINUTES));
        verification.setAttempts(0);
        verification.setVerified(false);
        verification.setLastSentAt(LocalDateTime.now());

        otpVerificationRepository.save(verification);

        emailService.sendOtpEmail(target, otp);
    }

    @Override
    @Transactional
    public void invalidateOtp(String target) {
        logger.info("Invalidating all OTP verifications for: {}", target);
        otpVerificationRepository.deleteByTarget(target);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isValidResetToken(String target, String resetToken) {
        Optional<OtpVerification> verificationOpt = otpVerificationRepository.findByTargetAndResetToken(target, resetToken);
        if (verificationOpt.isEmpty()) {
            return false;
        }

        OtpVerification verification = verificationOpt.get();
        return verification.isVerified() && verification.getExpiryTime().isAfter(LocalDateTime.now());
    }

    private String generateOtpCode() {
        int code = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(code);
    }

    private String hashOtp(String otp) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(otp.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException("Error hashing OTP code", ex);
        }
    }
}
