package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.UserResponse;
import com.sankar.aicip.dto.request.LoginRequest;
import com.sankar.aicip.dto.response.LoginResponse;
public interface UserService {

    UserResponse registerUser(UserRegistrationRequest request);
    LoginResponse loginUser(LoginRequest request);

}