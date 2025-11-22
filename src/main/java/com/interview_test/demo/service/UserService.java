package com.interview_test.demo.service;

import com.interview_test.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getUser(Long id);
    User saveUser(User user);
    User updateUser(Long id, User user);
    User deleteUser(Long id);
}
