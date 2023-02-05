package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.rowmapper.RoleRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.IRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class RoleDaoImpl implements IRoleDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly=true)
    public List<Role> getRoles() {
        return jdbcTemplate.query("select * from users",
                new RoleRowMapper());
    }
}
