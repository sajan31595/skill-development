package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.dto.rowmapper.UserRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.IUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly=true)
    public List<User> getUsers() {
        return jdbcTemplate.query("select * from users",
                new UserRowMapper());
    }

    @Override
    @Transactional(readOnly=true)
    public User findUserById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from users where id=?",
                new Object[]{id}, new int[]{}, new UserRowMapper());
    }

    @Transactional(readOnly=true)
    public User findUserByName(String userName) {
        return jdbcTemplate.queryForObject(
                "select * from users where lower(name)=?",
                new Object[]{userName.toLowerCase()}, new int[]{Types.VARCHAR}, new UserRowMapper());
    }

    @Transactional(readOnly=true)
    public User findUserByEmail(String email) {
        return jdbcTemplate.queryForObject(
                "select * from users where lower(email)=?",
                new Object[]{email.toLowerCase()}, new int[]{Types.VARCHAR}, new UserRowMapper());
    }

    public User createUser(final User user) {
        final String sql = "INSERT INTO USERS(ID, NAME, PASSWORD, AGE, EMAIL, PHONE, DOB, SEX, STATUS )" +
                " VALUES (nextval('users_seq'), ?, ?, ?, ?, ?, CURRENT_DATE, ?, 'AC')";
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, user.getUsername());
                    ps.setString(2, user.getPassword());
                    ps.setInt(3, user.getAge());
                    ps.setString(4, user.getEmail());
                    ps.setString(5, user.getPhoneNumber());
                    ps.setString(6, user.getSex());
                    return ps;
                }
            }, holder);
            return findUserByName(user.getUsername());
        }
        catch(Exception ex) {
            return null;
        }
    }

    @Override
    public List<String> getRoles(int userId) {
        final String sql = "select r.name from users u, roles r, user_roles ur \n" +
                "where  u.id = ur.user_id and r.id = ur.role_id and u.id = ?";
        List<String> roles = null;
        try {
            roles = jdbcTemplate.queryForList(sql, new Object[]{userId}, new int[]{Types.INTEGER}, String.class);
        }
        catch(Exception ex) {
            return null;
        }
        return roles;
    }

    @Override
    public void addUserToRoles(int userId, List<Integer> roleIds) {
        final String sql = "INSERT INTO USER_ROLES(ID, USER_ID, ROLE_ID, CREATED_ON, MODIFIED_ON, STATUS )" +
                " VALUES (nextval('user_roles_seq'), ?, ?, CURRENT_DATE, CURRENT_DATE, 'AC')";
        for (Integer roleId: roleIds) {
            try {
                KeyHolder holder = new GeneratedKeyHolder();
                jdbcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setInt(1, userId);
                        ps.setInt(3, roleId);
                        return ps;
                    }
                }, holder);
            }
            catch(Exception ex) {
            }
        }

    }
}

