package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.response.LoginResponse;
import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.response.CurrentUserResponse;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.entity.RefreshToken;
import com.sankar.aicip.enums.UserRole;
import com.sankar.aicip.repository.UserRepository;
import com.sankar.aicip.security.jwt.JwtService;
import com.sankar.aicip.service.impl.UserServiceImpl;
import com.sankar.aicip.exception.EmailAlreadyExistsException;
import com.sankar.aicip.exception.InvalidCredentialsException;
import com.sankar.aicip.exception.ResourceNotFoundException;
import com.sankar.aicip.service.RefreshTokenService;
import org.springframework.security.core.userdetails.UserDetails;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {
    private UserRegistrationRequest registrationRequest;
    private LoginRequest loginRequest;
    private User user;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private RefreshTokenService refreshTokenService;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {

        registrationRequest = new UserRegistrationRequest();
        registrationRequest.setFullName("Sankar P");
        registrationRequest.setEmail("sankar@example.com");
        registrationRequest.setPassword("Password123");
        registrationRequest.setPhoneNumber("9876543210");

        loginRequest = new LoginRequest();
        loginRequest.setEmail("sankar@example.com");
        loginRequest.setPassword("Password123");

        user = new User();
        user.setId(1L);
        user.setFullName("Sankar P");
        user.setEmail("sankar@example.com");
        user.setPassword("encodedPassword");
        user.setPhoneNumber("9876543210");
        user.setRole(UserRole.CITIZEN);
    }

    @Test
    void registerUser_ShouldRegisterSuccessfully() {

        // Arrange
        when(userRepository.findByEmail(registrationRequest.getEmail()))
                .thenReturn(Optional.empty());

        when(passwordEncoder.encode(registrationRequest.getPassword()))
                .thenReturn("encodedPassword");

        when(userRepository.save(any(User.class)))
                .thenReturn(user);

        // Act
        UserResponse response = userService.registerUser(registrationRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Sankar P", response.getFullName());
        assertEquals("sankar@example.com", response.getEmail());
        assertEquals("9876543210", response.getPhoneNumber());
        assertEquals(UserRole.CITIZEN, response.getRole());

        verify(userRepository, times(1))
                .findByEmail(registrationRequest.getEmail());

        verify(passwordEncoder, times(1))
                .encode(registrationRequest.getPassword());

        verify(userRepository, times(1))
                .save(any(User.class));
        verifyNoMoreInteractions(
                userRepository,
                passwordEncoder
        );
    }

    @Test
    void registerUser_ShouldThrowException_WhenEmailAlreadyExists() {

        // Arrange
        when(userRepository.findByEmail(registrationRequest.getEmail()))
                .thenReturn(Optional.of(user));

        // Act & Assert
        EmailAlreadyExistsException exception = assertThrows(
                EmailAlreadyExistsException.class,
                () -> userService.registerUser(registrationRequest)
        );

        assertEquals("Email already exists.", exception.getMessage());

        verify(userRepository, times(1))
                .findByEmail(registrationRequest.getEmail());

        verify(userRepository, never())
                .save(any(User.class));

        verify(passwordEncoder, never())
                .encode(anyString());

        verifyNoMoreInteractions(
                userRepository,
                passwordEncoder
        );
    }

    @Test
    void loginUser_ShouldLoginSuccessfully() {

        // Arrange
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken("refresh-token-123");

        when(userRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()))
                .thenReturn(true);

        when(jwtService.generateToken(any(UserDetails.class)))
                .thenReturn("jwt-token-123");

        when(refreshTokenService.createRefreshToken(user))
                .thenReturn(refreshToken);

        // Act
        LoginResponse response = userService.loginUser(loginRequest);

        // Assert
        assertNotNull(response);
        assertEquals("Sankar P", response.getFullName());
        assertEquals("sankar@example.com", response.getEmail());
        assertEquals("9876543210", response.getPhoneNumber());
        assertEquals("CITIZEN", response.getRole());
        assertEquals("jwt-token-123", response.getToken());
        assertEquals("refresh-token-123", response.getRefreshToken());
        assertNotNull(response.getLoginTime());

        verify(userRepository, times(1))
                .findByEmail(loginRequest.getEmail());

        verify(passwordEncoder, times(1))
                .matches(loginRequest.getPassword(), user.getPassword());

        verify(jwtService, times(1))
                .generateToken(any(UserDetails.class));

        verify(refreshTokenService, times(1))
                .createRefreshToken(user);

        verifyNoMoreInteractions(
                userRepository,
                passwordEncoder,
                jwtService,
                refreshTokenService
        );
    }

    @Test
    void loginUser_ShouldThrowException_WhenUserNotFound() {

        // Arrange
        when(userRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.empty());

        // Act & Assert
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> userService.loginUser(loginRequest)
        );

        assertEquals("Invalid email or password.", exception.getMessage());

        verify(userRepository, times(1))
                .findByEmail(loginRequest.getEmail());

        verify(passwordEncoder, never())
                .matches(anyString(), anyString());

        verify(jwtService, never())
                .generateToken(any(UserDetails.class));

        verify(refreshTokenService, never())
                .createRefreshToken(any(User.class));

        verifyNoMoreInteractions(
                userRepository,
                passwordEncoder,
                jwtService,
                refreshTokenService
        );
    }

    @Test
    void loginUser_ShouldThrowException_WhenPasswordIsInvalid() {

        // Arrange
        when(userRepository.findByEmail(loginRequest.getEmail()))
                .thenReturn(Optional.of(user));

        when(passwordEncoder.matches(
                loginRequest.getPassword(),
                user.getPassword()))
                .thenReturn(false);

        // Act & Assert
        InvalidCredentialsException exception = assertThrows(
                InvalidCredentialsException.class,
                () -> userService.loginUser(loginRequest)
        );

        assertEquals("Invalid email or password.", exception.getMessage());

        verify(userRepository, times(1))
                .findByEmail(loginRequest.getEmail());

        verify(passwordEncoder, times(1))
                .matches(loginRequest.getPassword(), user.getPassword());

        verify(jwtService, never())
                .generateToken(any(UserDetails.class));

        verify(refreshTokenService, never())
                .createRefreshToken(any(User.class));

        verifyNoMoreInteractions(
                userRepository,
                passwordEncoder,
                jwtService,
                refreshTokenService
        );
    }

    @Test
    void getCurrentUser_ShouldReturnUserProfileSuccessfully() {

        // Arrange
        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.of(user));

        // Act
        CurrentUserResponse response =
                userService.getCurrentUser(user.getEmail());

        // Assert
        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getFullName(), response.getFullName());
        assertEquals(user.getEmail(), response.getEmail());
        assertEquals(user.getPhoneNumber(), response.getPhoneNumber());
        assertEquals(user.getRole().name(), response.getRole());

        verify(userRepository, times(1))
                .findByEmail(user.getEmail());

        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void getCurrentUser_ShouldThrowException_WhenUserNotFound() {

        // Arrange
        when(userRepository.findByEmail(user.getEmail()))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(
                ResourceNotFoundException.class,
                () -> userService.getCurrentUser(user.getEmail())
        );

        assertEquals("User not found.", exception.getMessage());

        verify(userRepository, times(1))
                .findByEmail(user.getEmail());

        verifyNoMoreInteractions(userRepository);
    }
}