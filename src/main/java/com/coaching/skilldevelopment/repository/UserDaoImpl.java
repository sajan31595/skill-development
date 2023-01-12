package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.dto.rowmapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.List;

@Repository
public class UserDaoImpl implements IUserDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly=true)
    public List<User> findAll() {
        return jdbcTemplate.query("select * from users",
                new UserRowMapper());
    }

    @Transactional(readOnly=true)
    public User findUserById(int id) {
        return jdbcTemplate.queryForObject(
                "select * from users where id=?",
                new Object[]{id}, new int[]{}, new UserRowMapper());
    }

    @Transactional(readOnly=true)
    public User findUserByName(String userName) {
        return jdbcTemplate.queryForObject(
                "select * from users where name=?",
                new Object[]{userName}, new int[]{Types.VARCHAR}, new UserRowMapper());
    }

    public User create(final User user) {
        final String sql = "insert into users(name,email) values(?,?)";

        KeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, user.getName());
                ps.setString(2, user.getEmail());
                return ps;
            }
        }, holder);

        int newUserId = holder.getKey().intValue();
        user.setId(String.valueOf(newUserId));
        return user;
    }

}

