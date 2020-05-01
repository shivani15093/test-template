package com.task.controller;

import com.task.constants.Constants;
import com.task.dto.request.LogInDto;
import com.task.dto.response.Control;
import com.task.dto.response.GenericResponse;
import com.task.jwt.service.UserAuthenticationService;
import com.task.model.UserModel;
import com.task.service.LogInSignUpService;
import com.task.service.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.task.jwt.userDetails.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = "/user")
public class LogInSignUpController {
    @Autowired
    UserRepoService userRepoService;

    @Autowired
    LogInSignUpService logInSignUpService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<?> aignUpMethod(@RequestBody UserModel userModel, HttpServletResponse response){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData(logInSignUpService.signUpService(userModel, response));
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @RequestMapping(value = "/logIn", method = RequestMethod.POST)
    public ResponseEntity<?> logInMethod(@RequestBody LogInDto logInDto, HttpServletResponse response){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData(logInSignUpService.logInService(logInDto, response));
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @RequestMapping(value = "/logOut", method = RequestMethod.POST)
    public ResponseEntity<?> logOutMethod(HttpServletRequest request, HttpServletResponse response){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData("Session Expired!");
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMethod(){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData(userRepoService.getAllUsers());
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @Autowired
    UserAuthenticationService userAuthenticationService;
    @RequestMapping(value = "/getToken", method = RequestMethod.GET)
    public ResponseEntity<?> getToken(@RequestBody JwtUserModel jwtUserModel){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData(userAuthenticationService.createJwtForAuth(jwtUserModel));
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }

    @RequestMapping(value = "/dummy", method = RequestMethod.GET)
    public ResponseEntity<?> dummyMethod(){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData("DUMMY DATA !");
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
    @RequestMapping(value = "/logOutSuccess", method = RequestMethod.GET)
    public ResponseEntity<?> logOutPage(){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        GenericResponse genericResponse = new GenericResponse();
        genericResponse.setControl(control);
        genericResponse.setData("Session Expired...Loging Out Successful!");
        return ResponseEntity.status(HttpStatus.OK).body(genericResponse);
    }
}
