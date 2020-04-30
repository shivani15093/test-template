package com.testtask.service;

import com.testtask.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserRepoService {
    public User saveUser(User user);
    public List<User> getAllUsers();
    public User getUserByEmail(String email);
}
