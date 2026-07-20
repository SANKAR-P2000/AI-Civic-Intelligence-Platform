package com.sankar.aicip.service;

import com.sankar.aicip.dto.request.UserRegistrationRequest;
import com.sankar.aicip.dto.response.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest request);

}