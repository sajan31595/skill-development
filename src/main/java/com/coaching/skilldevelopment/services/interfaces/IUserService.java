package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.exception.InvalidRequestException;

import java.util.List;
import java.util.Map;

public interface IUserService {

    List<User> getUsers();

    User findByEmail(String email);

    User findByUsername(String email);

    User saveUser(User user);

    boolean isUsernameExists(String name);

    List<Role.RoleType> getRoles(int userId);

    void addUserToRoles(Map<Integer, List<Integer>> userToRoles) throws InvalidRequestException;
}
