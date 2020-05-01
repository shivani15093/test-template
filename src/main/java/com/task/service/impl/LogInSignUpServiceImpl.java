package com.task.service.impl;

import com.task.MainApplication;
import com.task.dto.request.LogInDto;
import com.task.jwt.service.UserAuthenticationService;
import com.task.jwt.userDetails.JwtUserModel;
import com.task.model.UserModel;
import com.task.service.LogInSignUpService;
import com.task.service.UserRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class LogInSignUpServiceImpl implements LogInSignUpService {
    @Autowired
    UserRepoService userRepoService;
    @Autowired
    UserAuthenticationService userAuthenticationService;

    private static final Logger log = LoggerFactory.getLogger(LogInSignUpServiceImpl.class);

    @Override
    public UserModel logInService(LogInDto logInCredentials, HttpServletResponse response) {
        UserModel loggedInUser = userRepoService.getUserByEmail(logInCredentials.getEmail());
        if(loggedInUser.getPassword().equals(logInCredentials.getPassword())){
            log.info("Credentials Matched, LogIn Successful!");
            JwtUserModel jwtUserModel = new JwtUserModel();
            jwtUserModel.setEmail(loggedInUser.getEmail());
            jwtUserModel.setLastName(loggedInUser.getLastName());
            String jwt = userAuthenticationService.createJwtForAuth(jwtUserModel);
            Cookie cookie = new Cookie("USER_SESSION", jwt);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            log.info("Creating user session");
            response.addCookie(cookie);
            return loggedInUser;

        }
        else {
            log.info("Credentials not Matched, LogIn Failed!");
            return null;
        }

    }

    @Override
    public UserModel signUpService(UserModel userModel, HttpServletResponse response) {
        UserModel signedInUser = userRepoService.saveUser(userModel);
        if(signedInUser != null)
        {
            log.info("Sucessfully SignedUp!");
            JwtUserModel jwtUserModel = new JwtUserModel();
            jwtUserModel.setEmail(signedInUser.getEmail());
            jwtUserModel.setLastName(signedInUser.getLastName());
            String jwt = userAuthenticationService.createJwtForAuth(jwtUserModel);
            Cookie cookie = new Cookie("USER_SESSION", jwt);
            cookie.setPath("/");
            cookie.setMaxAge(3600);
            response.addCookie(cookie);
            return signedInUser;
        }
        else{
            log.info("SignUp Unsuccessful!");
            return null;

        }


    }
}
