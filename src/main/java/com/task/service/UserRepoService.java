package com.task.service;

import com.task.model.UserModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepoService {
    public UserModel saveUser(UserModel userModel);
    public List<UserModel> getAllUsers();
    public UserModel getUserByEmail(String email);
}
