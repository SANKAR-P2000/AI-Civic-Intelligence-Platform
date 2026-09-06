package com.sankar.aicip.controller;

import jakarta.validation.Valid;
import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.response.LoginResponse;
import com.sankar.aicip.dto.response.CurrentUserResponse;
import com.sankar.aicip.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import com.sankar.aicip.dto.request.UpdateProfileRequest;
import com.sankar.aicip.dto.request.ChangePasswordRequest;
import com.sankar.aicip.dto.response.UserProfileResponse;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Tag(
        name = "User Management",
        description = "APIs for user registration, authentication, and profile management"
)
@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Register a new user",
            description = "Registers a new citizen account."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Validation failed"),
            @ApiResponse(responseCode = "409", description = "Email already exists")
    })
    @PostMapping("/register")
    public ResponseEntity<UserResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {
        logger.info("Registration request received for email: {}",
                request.getEmail());

        UserResponse response = userService.registerUser(request);

        logger.info("User registered successfully: {}",
                response.getEmail());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(
            summary = "User Login",
            description = "Authenticates the user and returns JWT access and refresh tokens."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login successful"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(
            @Valid @RequestBody LoginRequest request) {
        logger.info("Login request received for email: {}",
                request.getEmail());
        LoginResponse response = userService.loginUser(request);

        logger.info("User logged in successfully: {}",
                response.getEmail());

        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Get Authenticated Profile",
            description = "Returns profile details for the logged-in user."
    )
    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponse> getProfile(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        String email = authentication.getName();
        logger.info("Fetching profile for: {}", email);
        UserProfileResponse response = userService.getUserProfile(email);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Update Authenticated Profile",
            description = "Updates full name and phone number for the logged-in user."
    )
    @PatchMapping("/profile")
    public ResponseEntity<UserProfileResponse> updateProfile(
            Authentication authentication,
            @Valid @RequestBody UpdateProfileRequest request) {
        String email = authentication.getName();
        logger.info("Updating profile for: {}", email);
        UserProfileResponse response = userService.updateUserProfile(email, request);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Upload Profile Picture",
            description = "Uploads and updates avatar profile picture for logged-in user."
    )
    @PostMapping("/profile/picture")
    public ResponseEntity<UserProfileResponse> updateProfilePicture(
            Authentication authentication,
            @RequestParam("file") MultipartFile file) {
        String email = authentication.getName();
        logger.info("Uploading profile picture for: {}", email);
        UserProfileResponse response = userService.updateProfilePicture(email, file);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "Change Password",
            description = "Changes password for the logged-in user."
    )
    @PatchMapping("/change-password")
    public ResponseEntity<Map<String, String>> changePassword(
            Authentication authentication,
            @Valid @RequestBody ChangePasswordRequest request) {
        String email = authentication.getName();
        logger.info("Password change requested for: {}", email);
        userService.changePassword(email, request);
        return ResponseEntity.ok(Map.of("message", "Password changed successfully."));
    }

    @Operation(
            summary = "Get Current User",
            description = "Returns details of the authenticated user."
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User details returned successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public ResponseEntity<CurrentUserResponse> getCurrentUser(
            Authentication authentication) {

        logger.info("Fetching current user details for: {}",
                authentication.getName());

        String email = authentication.getName();

        CurrentUserResponse response =
                userService.getCurrentUser(email);

        logger.info("Current user details returned successfully.");

        return ResponseEntity.ok(response);
    }

}