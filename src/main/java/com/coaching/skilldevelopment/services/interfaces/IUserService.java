package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.exception.InvalidRequestException;

import java.util.List;

public interface IUserService {

    List<User> getUsers();

    User getUserById(int id);

    User findByEmail(String email);

    User findByUsername(String email);

    User saveUser(User user);

    void updateUser(int userId, User user);

    void deleteUser(int userId);

    boolean isUsernameExists(String name);

    List<Role.RoleType> getRoles(int userId);

    void addUserToRoles(int roleId, List<Integer> userIds) throws InvalidRequestException;
}
