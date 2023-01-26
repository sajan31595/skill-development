package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
    User findUserByName(String userName);
    User createUser(final User user);
}
