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

@RestController
@RequestMapping("/api/users")
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

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