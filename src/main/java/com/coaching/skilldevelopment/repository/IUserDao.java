package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface IUserDao {

    List<User> findAll();
    User findUserByName(String userName);
    User create(final User user);
}
