package com.task.jwt.service.impl;

import com.task.jwt.utils.JwtUtil;
import com.task.jwt.userDetails.*;
import com.task.jwt.service.UserAuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl implements UserAuthenticationService {
    @Autowired
    JwtUtil jwtUtil;

    static Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    @Override
    public String createJwtForAuth(JwtUserModel jwtUserModel) {
        UserDetails userDetails = new UserDetails();
        userDetails.setJwtUserModel(jwtUserModel);
        logger.info("Calling generateToken method");
        String jwt = jwtUtil.generateToken(userDetails);
        return jwt;
    }

}
