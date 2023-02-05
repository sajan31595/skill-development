package com.coaching.skilldevelopment.payload;

import java.util.List;
import java.util.Map;

public class UserRoleRequest {

    private Map<Integer, List<Integer>> userToRoles;
    private Map<Integer, List<Integer>> roleToUsers;

    public Map<Integer, List<Integer>> getUserToRoles() {
        return userToRoles;
    }

    public void setUserToRoles(Map<Integer, List<Integer>> userToRoles) {
        this.userToRoles = userToRoles;
    }

    public Map<Integer, List<Integer>> getRoleToUsers() {
        return roleToUsers;
    }

    public void setRoleToUsers(Map<Integer, List<Integer>> roleToUsers) {
        this.roleToUsers = roleToUsers;
    }
}
