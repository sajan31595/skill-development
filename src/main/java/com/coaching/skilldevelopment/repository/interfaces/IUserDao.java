package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface IUserDao {

    List<User> getUsers();
    User findUserById(int userId);
    User findUserByName(String userName);
    User findUserByEmail(String email);
    User createUser(final User user);
    void updateUser(int userId, User user);
    void deleteUser(int userId);
    List<String> getRoles(int userId);
    void addUserToRoles(int userId, int roleId);
}
