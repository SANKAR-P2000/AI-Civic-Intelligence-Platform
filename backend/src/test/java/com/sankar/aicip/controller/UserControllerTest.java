package com.sankar.aicip.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.CurrentUserResponse;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.response.LoginResponse;
import com.sankar.aicip.service.UserService;
import com.sankar.aicip.exception.GlobalExceptionHandler;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.exception.InvalidCredentialsException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import static org.springframework.http.MediaType.APPLICATION_JSON;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;


class UserControllerTest {

    private MockMvc mockMvc;

    private ObjectMapper objectMapper;

    private UserService userService;

    @BeforeEach
    void setUp() {

        userService = mock(UserService.class);

        UserController userController =
                new UserController(userService);

        LocalValidatorFactoryBean validator =
                new LocalValidatorFactoryBean();

        validator.afterPropertiesSet();

        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .setValidator(validator)
                .build();

        objectMapper = new ObjectMapper();
    }

    @Test
    void registerUser_ShouldReturnCreated() throws Exception {

        // Arrange
        UserRegistrationRequest request =
                new UserRegistrationRequest();

        request.setFullName("Sankar P");
        request.setEmail("sankar@example.com");
        request.setPassword("Password123");
        request.setPhoneNumber("9876543210");

        UserResponse response =
                new UserResponse();

        response.setId(1L);
        response.setFullName("Sankar P");
        response.setEmail("sankar@example.com");
        response.setPhoneNumber("9876543210");

        when(userService.registerUser(any(UserRegistrationRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(
                        post("/api/users/register")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Sankar P"))
                .andExpect(jsonPath("$.email").value("sankar@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("9876543210"));
        // Verify controller-to-service interaction
        ArgumentCaptor<UserRegistrationRequest> requestCaptor =
                ArgumentCaptor.forClass(UserRegistrationRequest.class);

        verify(userService)
                .registerUser(requestCaptor.capture());

        UserRegistrationRequest capturedRequest =
                requestCaptor.getValue();

        assertEquals(
                "Sankar P",
                capturedRequest.getFullName()
        );

        assertEquals(
                "sankar@example.com",
                capturedRequest.getEmail()
        );

        assertEquals(
                "Password123",
                capturedRequest.getPassword()
        );

        assertEquals(
                "9876543210",
                capturedRequest.getPhoneNumber()
        );
    }


    @Test
    void registerUser_ShouldReturnBadRequest_WhenPhoneNumberIsInvalid()
            throws Exception {

        // Arrange
        UserRegistrationRequest request =
                new UserRegistrationRequest();

        request.setFullName("Sankar P");
        request.setEmail("sankar@example.com");
        request.setPassword("Password123");

        // Invalid phone number:
        // @Pattern requires exactly 10 digits.
        request.setPhoneNumber("12345");

        // Act & Assert
        mockMvc.perform(
                        post("/api/users/register")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isBadRequest());

        verify(userService, never())
                .registerUser(any(UserRegistrationRequest.class));
    }

    @Test
    void loginUser_ShouldReturnOk() throws Exception {

        // Arrange
        LoginRequest request =
                new LoginRequest();

        request.setEmail("sankar@example.com");
        request.setPassword("Password123");

        LoginResponse response =
                new LoginResponse();

        response.setId(1L);
        response.setFullName("Sankar P");
        response.setEmail("sankar@example.com");
        response.setPhoneNumber("9876543210");
        response.setRole("CITIZEN");
        response.setLoginTime(LocalDateTime.of(2026, 8, 9, 10, 30));
        response.setToken("jwt-token-123");
        response.setRefreshToken("refresh-token-123");

        when(userService.loginUser(any(LoginRequest.class)))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(
                        post("/api/users/login")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Sankar P"))
                .andExpect(jsonPath("$.email").value("sankar@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("9876543210"))
                .andExpect(jsonPath("$.role").value("CITIZEN"))
                .andExpect(jsonPath("$.token").value("jwt-token-123"))
                .andExpect(jsonPath("$.refreshToken").value("refresh-token-123"))
                .andExpect(
                        jsonPath("$.loginTime")
                                .value("2026-08-09T10:30:00")
                );
        // Verify controller-to-service interaction
        ArgumentCaptor<LoginRequest> requestCaptor =
                ArgumentCaptor.forClass(LoginRequest.class);

        verify(userService)
                .loginUser(requestCaptor.capture());

        LoginRequest capturedRequest =
                requestCaptor.getValue();

        assertEquals(
                "sankar@example.com",
                capturedRequest.getEmail()
        );

        assertEquals(
                "Password123",
                capturedRequest.getPassword()
        );
    }

    @Test
    void loginUser_ShouldReturnBadRequest_WhenEmailIsInvalid()
            throws Exception {

        // Arrange
        LoginRequest request =
                new LoginRequest();

        request.setEmail("invalid-email");
        request.setPassword("Password123");

        // Act & Assert
        mockMvc.perform(
                        post("/api/users/login")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isBadRequest());

        verify(userService, never())
                .loginUser(any(LoginRequest.class));
    }

    @Test
    void loginUser_ShouldReturnUnauthorized_WhenCredentialsAreInvalid()
            throws Exception {

        // Arrange
        LoginRequest request =
                new LoginRequest();

        request.setEmail("sankar@example.com");
        request.setPassword("WrongPassword123");

        when(userService.loginUser(any(LoginRequest.class)))
                .thenThrow(
                        new InvalidCredentialsException(
                                "Invalid email or password."
                        )
                );

        LocalValidatorFactoryBean validator =
                new LocalValidatorFactoryBean();

        validator.afterPropertiesSet();

        MockMvc exceptionMockMvc =
                MockMvcBuilders
                        .standaloneSetup(
                                new UserController(userService)
                        )
                        .setValidator(validator)
                        .setControllerAdvice(
                                new GlobalExceptionHandler()
                        )
                        .build();

        // Act & Assert
        exceptionMockMvc.perform(
                        post("/api/users/login")
                                .contentType(APPLICATION_JSON)
                                .content(
                                        objectMapper.writeValueAsString(request)
                                )
                )
                .andExpect(status().isUnauthorized())
                .andExpect(
                        jsonPath("$.status").value(401)
                )
                .andExpect(
                        jsonPath("$.error").value("Unauthorized")
                )
                .andExpect(
                        jsonPath("$.message")
                                .value("Invalid email or password.")
                )
                .andExpect(
                        jsonPath("$.path")
                                .value("/api/users/login")
                );
    }

    @Test
    void getProfile_ShouldReturnOk() throws Exception {

        // Act & Assert
        mockMvc.perform(
                        get("/api/users/profile")
                )
                .andExpect(status().isOk())
                .andExpect(
                        jsonPath("$").value("Authenticated User Profile")
                );
    }

    @Test
    void getCurrentUser_ShouldReturnOk() throws Exception {

        // Arrange
        Authentication authentication =
                mock(Authentication.class);

        when(authentication.getName())
                .thenReturn("sankar@example.com");

        CurrentUserResponse response =
                new CurrentUserResponse();

        response.setId(1L);
        response.setFullName("Sankar P");
        response.setEmail("sankar@example.com");
        response.setPhoneNumber("9876543210");
        response.setRole("CITIZEN");
        response.setCreatedAt(
                LocalDateTime.of(2026, 8, 2, 10, 30, 45)
        );

        when(userService.getCurrentUser("sankar@example.com"))
                .thenReturn(response);

        // Act & Assert
        mockMvc.perform(
                        get("/api/users/me")
                                .principal(authentication)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.fullName").value("Sankar P"))
                .andExpect(jsonPath("$.email").value("sankar@example.com"))
                .andExpect(jsonPath("$.phoneNumber").value("9876543210"))
                .andExpect(jsonPath("$.role").value("CITIZEN"))
                .andExpect(
                        jsonPath("$.createdAt")
                                .value("2026-08-02T10:30:45")
                );
        // Verify controller-to-service interaction
        verify(userService)
                .getCurrentUser("sankar@example.com");
    }

    @Test
    void getCurrentUser_ShouldReturnNotFound_WhenUserDoesNotExist()
            throws Exception {

        // Arrange
        Authentication authentication =
                mock(Authentication.class);

        when(authentication.getName())
                .thenReturn("unknown@example.com");

        when(userService.getCurrentUser("unknown@example.com"))
                .thenThrow(
                        new ResourceNotFoundException("User not found.")
                );

        LocalValidatorFactoryBean validator =
                new LocalValidatorFactoryBean();

        validator.afterPropertiesSet();

        MockMvc exceptionMockMvc =
                MockMvcBuilders
                        .standaloneSetup(
                                new UserController(userService)
                        )
                        .setValidator(validator)
                        .setControllerAdvice(
                                new GlobalExceptionHandler()
                        )
                        .build();

        // Act & Assert
        exceptionMockMvc.perform(
                        get("/api/users/me")
                                .principal(authentication)
                )
                .andExpect(status().isNotFound())
                .andExpect(
                        jsonPath("$.status").value(404)
                )
                .andExpect(
                        jsonPath("$.error").value("Not Found")
                )
                .andExpect(
                        jsonPath("$.message")
                                .value("User not found.")
                )
                .andExpect(
                        jsonPath("$.path")
                                .value("/api/users/me")
                );
    }
}