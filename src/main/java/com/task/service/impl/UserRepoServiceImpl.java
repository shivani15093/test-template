package com.task.service.impl;

import com.task.model.UserModel;
import com.task.repository.UserRepo;
import com.task.service.UserRepoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserRepoServiceImpl implements UserRepoService {
    @Autowired
    UserRepo userRepo;

    @Override
    public UserModel saveUser(UserModel userModel) {
        return userRepo.save(userModel);
    }

    @Override
    public List<UserModel> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserModel getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }
}
