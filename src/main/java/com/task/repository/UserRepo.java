package com.task.repository;

import com.task.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<UserModel, String> {
    public UserModel save(UserModel userModel);
    public List<UserModel> findAll();
    public UserModel findByEmail(String email);
}
