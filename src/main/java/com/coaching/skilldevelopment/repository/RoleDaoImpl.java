package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.dto.rowmapper.RoleRowMapper;
import com.coaching.skilldevelopment.dto.rowmapper.UserRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Types;
import java.util.List;

@Repository
public class RoleDaoImpl implements IRoleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly=true)
    public List<Role> getRoles() {
        return jdbcTemplate.query("select * from roles",
                new RoleRowMapper());
    }

    @Override
    @Transactional(readOnly=true)
    public Role getRole(int roleId) {
        String sql = "select * from roles where id=" + roleId;
        Role role = null;
        try{
            role = jdbcTemplate.queryForObject(sql, new RoleRowMapper());
        } catch(Exception ex){
            return null;
        }
        return role;
    }

    @Override
    @Transactional(readOnly=true)
    public boolean isUserExistInRole(int roleId, int userId) {
        String sql = "select id from user_roles where role_id= ? and user_id =?";
        Long userRoleId;
        try{
            userRoleId = jdbcTemplate.queryForObject(sql,
                    new Object[]{roleId, userId}, new int[]{Types.INTEGER, Types.INTEGER}, Long.class);;
        } catch(Exception ex){
            return false;
        }
        return userRoleId>0;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getUsers(int roleId) {
        String sql = "select u.* from user_roles ur " +
                "inner join users u on u.id = ur.user_id where ur.role_id=" + roleId;
        List<User> users = null;
        try{
            users = jdbcTemplate.query(sql, new UserRowMapper());
        } catch(Exception ex){
            return null;
        }
        return users;
    }
}
