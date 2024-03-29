package com.coaching.skilldevelopment.payload;

import java.util.List;

public class UserRoleRequest {

    private int roleId;
    private List<Integer> userIds;

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
