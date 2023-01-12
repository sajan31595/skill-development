package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    User findByEmail(String email);

    void saveUser(User user);
}
