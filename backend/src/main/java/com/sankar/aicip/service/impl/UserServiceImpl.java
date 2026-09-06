package com.sankar.aicip.service.impl;

import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.request.UpdateProfileRequest;
import com.sankar.aicip.dto.request.ChangePasswordRequest;
import com.sankar.aicip.dto.response.LoginResponse;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.response.CurrentUserResponse;
import com.sankar.aicip.dto.response.UserProfileResponse;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.enums.UserRole;
import com.sankar.aicip.exception.EmailAlreadyExistsException;
import com.sankar.aicip.exception.InvalidCredentialsException;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.exception.BadRequestException;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.service.UserService;
import com.sankar.aicip.service.RefreshTokenService;
import com.sankar.aicip.service.OtpService;
import com.sankar.aicip.service.storage.FileStorageService;
import com.sankar.aicip.security.jwt.JwtService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger =
            LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final OtpService otpService;
    private final FileStorageService fileStorageService;

    public UserServiceImpl(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService,
            AuthenticationManager authenticationManager,
            RefreshTokenService refreshTokenService,
            @Qualifier("emailOtpService") OtpService otpService,
            FileStorageService fileStorageService
    ) {

        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
        this.otpService = otpService;
        this.fileStorageService = fileStorageService;
    }

    @Override
    public UserResponse registerUser(UserRegistrationRequest request) {
        logger.info("Registering new user with email: {}",
                request.getEmail());
        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        User user = new User();

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhoneNumber(request.getPhoneNumber());
        user.setRole(UserRole.CITIZEN);
        user.setEmailVerified(false);
        user.setPhoneVerified(false);

        User savedUser = userRepository.save(user);

        logger.info("User registered successfully. User ID: {}, Email: {}",
                savedUser.getId(),
                savedUser.getEmail());
        UserResponse response = new UserResponse();

        response.setId(savedUser.getId());
        response.setFullName(savedUser.getFullName());
        response.setEmail(savedUser.getEmail());
        response.setPhoneNumber(savedUser.getPhoneNumber());
        response.setRole(savedUser.getRole());
        response.setCreatedAt(savedUser.getCreatedAt());

        return response;
    }

    @Override
    public LoginResponse loginUser(LoginRequest request) {
        logger.info("Login attempt for email: {}",
                request.getEmail());

        User user = userRepository.findByEmail(request.getEmail())

                .orElseThrow(() ->
                        new InvalidCredentialsException("Invalid email or password."));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException("Invalid email or password.");
        }


        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(user.getEmail())
                        .password(user.getPassword())
                        .roles(user.getRole().name())
                        .build();

        String token = jwtService.generateToken(userDetails);
        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user);
        LoginResponse response = new LoginResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole().name());
        response.setLoginTime(LocalDateTime.now());
        response.setToken(token);
        response.setRefreshToken(refreshToken.getToken());
        logger.info("User logged in successfully. User ID: {}, Email: {}",
                user.getId(),
                user.getEmail());
        return response;
    }

    @Override
    public CurrentUserResponse getCurrentUser(String email) {
        logger.info("Fetching profile for user: {}",
                email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found."));

        CurrentUserResponse response = new CurrentUserResponse();

        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole().name());
        response.setEmailVerified(user.isEmailVerified());
        response.setPhoneVerified(user.isPhoneVerified());
        response.setProfilePictureUrl(user.getProfilePictureUrl());
        response.setCreatedAt(user.getCreatedAt());
        logger.info("Profile returned successfully for user: {}",
                user.getEmail());
        return response;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void resetPassword(String email, String resetToken, String newPassword) {
        logger.info("Attempting to reset password for email: {}", email);
        if (!otpService.isValidResetToken(email, resetToken)) {
            throw new BadRequestException("Invalid or expired reset token.");
        }

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        otpService.invalidateOtp(email);
        logger.info("Password reset successfully for email: {}", email);
    }

    @Override
    public UserProfileResponse getUserProfile(String email) {
        logger.info("Fetching UserProfileResponse for: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        UserProfileResponse response = new UserProfileResponse();
        response.setId(user.getId());
        response.setFullName(user.getFullName());
        response.setEmail(user.getEmail());
        response.setPhoneNumber(user.getPhoneNumber());
        response.setRole(user.getRole().name());
        response.setEmailVerified(user.isEmailVerified());
        response.setPhoneVerified(user.isPhoneVerified());
        response.setProfilePictureUrl(user.getProfilePictureUrl());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public UserProfileResponse updateUserProfile(String email, UpdateProfileRequest request) {
        logger.info("Updating user profile for: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        user.setFullName(request.getFullName());
        user.setPhoneNumber(request.getPhoneNumber());

        userRepository.save(user);
        logger.info("Profile updated successfully for user: {}", email);
        return getUserProfile(email);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void changePassword(String email, ChangePasswordRequest request) {
        logger.info("Attempting password change for user: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new BadRequestException("Current password is incorrect.");
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new BadRequestException("New password and confirmation password do not match.");
        }

        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
        logger.info("Password changed successfully for user: {}", email);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public UserProfileResponse updateProfilePicture(String email, MultipartFile file) {
        logger.info("Updating profile picture for user: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        String imagePath = fileStorageService.storeFile(file);
        user.setProfilePictureUrl(imagePath);
        userRepository.save(user);

        logger.info("Profile picture updated successfully for user: {}", email);
        return getUserProfile(email);
    }

    @Override
    public void sendEmailVerificationOtp(String email) {
        logger.info("Sending email verification OTP to: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (user.isEmailVerified()) {
            throw new BadRequestException("Email is already verified.");
        }

        otpService.sendOtp(email);
    }

    @Override
    @org.springframework.transaction.annotation.Transactional
    public void verifyEmailOtp(String email, String otp) {
        logger.info("Verifying email OTP for: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (user.isEmailVerified()) {
            throw new BadRequestException("Email is already verified.");
        }

        // Verify OTP code
        otpService.verifyOtpAndGenerateToken(email, otp);
        user.setEmailVerified(true);
        userRepository.save(user);
        otpService.invalidateOtp(email);

        logger.info("Email verified successfully for user: {}", email);
    }

    @Override
    public void resendEmailVerificationOtp(String email) {
        logger.info("Resending email verification OTP to: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found."));

        if (user.isEmailVerified()) {
            throw new BadRequestException("Email is already verified.");
        }

        otpService.resendOtp(email);
    }
}