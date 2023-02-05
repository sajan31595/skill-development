package com.coaching.skilldevelopment.dto;


public class Role {
    public enum RoleType {
        ADMIN,
        INSTRUCTOR,
        USER
    }

    private int roleId;
    private RoleType roletype;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public RoleType getRoletype() {
        return roletype;
    }

    public void setRoletype(RoleType roletype) {
        this.roletype = roletype;
    }
}
