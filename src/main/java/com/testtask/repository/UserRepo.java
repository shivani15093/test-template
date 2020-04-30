package com.testtask.repository;

import com.testtask.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepo extends CrudRepository<User, String> {
    public User save(User user);
    public List<User> findAll();
    public User findByEmail(String email);
}
