package com.coaching.skilldevelopment.dto.rowmapper;

import com.coaching.skilldevelopment.dto.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {

    @Override
    public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
        Course course = new Course();
        course.setId(rs.getInt("id"));
        return course;
    }
}
