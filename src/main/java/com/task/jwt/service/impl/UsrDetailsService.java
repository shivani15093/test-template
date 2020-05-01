package com.task.jwt.service.impl;

import com.task.jwt.userDetails.*;
import com.task.model.UserModel;
import com.task.service.UserRepoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsrDetailsService implements UserDetailsService {
    static Logger logger = LoggerFactory.getLogger(UsrDetailsService.class);
    @Autowired
    UserRepoService userRepoService;

    @Override
    public UserDetails loadUserByUsername(String email){
        //Find from Db and return data
        logger.info("Loading user details by user email");
        UserDetails userDetails = new UserDetails();
        UserModel userModel = userRepoService.getUserByEmail(email);

        JwtUserModel jwtUserModel = new JwtUserModel();
        if(userModel != null){
            jwtUserModel.setLastName(userModel.getLastName());
            jwtUserModel.setEmail(userModel.getEmail());
            userDetails.setJwtUserModel(jwtUserModel);
            return userDetails;
        }
        else
            return null;

    }
}
