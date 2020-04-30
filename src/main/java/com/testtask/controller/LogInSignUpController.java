package com.testtask.controller;

import com.testtask.constants.Constants;
import com.testtask.dto.Control;
import com.testtask.dto.Response;
import com.testtask.model.User;
import com.testtask.service.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class LogInSignUpController {
    @Autowired
    UserRepoService userRepoService;

    @RequestMapping(value = "/signUp", method = RequestMethod.POST)
    public ResponseEntity<?> logInMethod(@RequestBody User user){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        Response response = new Response();
        response.setControl(control);
        response.setData(userRepoService.saveUser(user));
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMethod(){
        Control control = new Control();
        control.setStatus(Constants.SUCCESS_STATUS);
        control.setMessage(Constants.SUCCESS_MESSAGE);
        Response response = new Response();
        response.setControl(control);
        response.setData(userRepoService.getAllUsers());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
