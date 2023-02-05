package com.coaching.skilldevelopment.dto.rowmapper;

import com.coaching.skilldevelopment.dto.Role;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {

    @Override
    public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
        Role role = new Role();
        role.setRoleId(rs.getInt("id"));
        role.setRoletype(Role.RoleType.valueOf(rs.getString("name")));
        return role;
    }
}
