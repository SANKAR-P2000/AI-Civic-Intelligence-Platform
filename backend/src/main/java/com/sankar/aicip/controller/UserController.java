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

    @GetMapping("/profile")
    public ResponseEntity<String> getProfile() {

        return ResponseEntity.ok("Authenticated User Profile");
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