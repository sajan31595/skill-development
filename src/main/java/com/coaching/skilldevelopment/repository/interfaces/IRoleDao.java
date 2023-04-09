package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface IRoleDao {

    List<Role> getRoles();
    Role getRole(int roleId);
    boolean isUserExistInRole(int roleId, int userId);
    List<User> getUsers(int roleId);
}
