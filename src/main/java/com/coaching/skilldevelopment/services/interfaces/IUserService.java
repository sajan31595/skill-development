package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    User findByEmail(String email);

    User findByUsername(String email);

    User saveUser(User user);

    boolean isUsernameExists(String name);
}
