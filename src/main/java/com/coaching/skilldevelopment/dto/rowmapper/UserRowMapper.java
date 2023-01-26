package com.coaching.skilldevelopment.dto.rowmapper;

import com.coaching.skilldevelopment.dto.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(String.valueOf(rs.getInt("id")));
        user.setUsername(rs.getString("name"));
        user.setEmail(rs.getString("email"));
        user.setPassword(rs.getString("password"));
        user.setPhoneNumber(rs.getString("phone"));
        user.setAge(rs.getInt("age"));
        user.setSex(User.SEX.valueOf(rs.getString("sex")));
        return user;
    }
}
