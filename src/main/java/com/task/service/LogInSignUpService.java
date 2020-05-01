package com.task.service;

import com.task.dto.request.LogInDto;
import com.task.model.UserModel;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public interface LogInSignUpService {
    public UserModel logInService(LogInDto logInCredentials,  HttpServletResponse response);
    public UserModel signUpService(UserModel userModel, HttpServletResponse response);
}
