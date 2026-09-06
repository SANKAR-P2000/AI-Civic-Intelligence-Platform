package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.response.LoginResponse;
import com.sankar.aicip.dto.response.CurrentUserResponse;
import com.sankar.aicip.dto.request.UpdateProfileRequest;
import com.sankar.aicip.dto.request.ChangePasswordRequest;
import com.sankar.aicip.dto.response.UserProfileResponse;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
    CurrentUserResponse getCurrentUser(String email);
    void resetPassword(String email, String resetToken, String newPassword);

    UserProfileResponse getUserProfile(String email);
    UserProfileResponse updateUserProfile(String email, UpdateProfileRequest request);
    void changePassword(String email, ChangePasswordRequest request);
    UserProfileResponse updateProfilePicture(String email, MultipartFile file);
    void sendEmailVerificationOtp(String email);
    void verifyEmailOtp(String email, String otp);
    void resendEmailVerificationOtp(String email);

}