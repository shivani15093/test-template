package com.task.jwt.service;

import com.task.jwt.userDetails.*;
import org.springframework.stereotype.Service;

@Service
public interface UserAuthenticationService {
    public String createJwtForAuth(JwtUserModel jwtUserModel);
}
