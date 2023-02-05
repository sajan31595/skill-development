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
        course.setName(rs.getString("name"));
        course.setType(rs.getString("type"));
        course.setAuthor_id(rs.getInt("author_id"));
        course.setGroup_link(rs.getString("group_link"));
        course.setStart_date(rs.getDate("start_date"));
        course.setCreated_on(rs.getDate("created_on"));
        course.setStatus(rs.getString("status"));
        return course;
    }
}
