package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.response.LoginResponse;
import com.sankar.aicip.dto.response.CurrentUserResponse;
public interface UserService {

    UserResponse registerUser(UserRegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);
    CurrentUserResponse getCurrentUser(String email);

}