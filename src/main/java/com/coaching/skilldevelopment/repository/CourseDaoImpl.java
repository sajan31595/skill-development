package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.rowmapper.CourseRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.ICourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CourseDaoImpl implements ICourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly=true)
    public List<Course> findAll() {
        return jdbcTemplate.query("select * from courses",
                new CourseRowMapper());
    }
}
