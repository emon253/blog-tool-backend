package com.yasin.blogapp.services;

import java.util.List;

import com.yasin.blogapp.entity.User;

public interface IUserService {

    List<User> getAllUsers();

    User getUserById(Long id);

    User createUser(User user);

    User updateUser(User user);

    void deleteUserById(Long id);

}
