package com.coaching.skilldevelopment.dto;


import java.util.List;

public class Role {
    public enum RoleType {
        ADMIN,
        INSTRUCTOR,
        USER
    }

    private int roleId;
    private RoleType roleType;
    private List<User> members;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoletype(RoleType roletype) {
        this.roleType = roletype;
    }

    public List<User> getMembers() {
        return members;
    }

    public void setMembers(List<User> members) {
        this.members = members;
    }
}
