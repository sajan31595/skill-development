package com.coaching.skilldevelopment.dto.rowmapper;

import com.coaching.skilldevelopment.dto.CourseUser;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseUserRowMapper implements RowMapper<CourseUser> {

    @Override
    public CourseUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        CourseUser courseUser = new CourseUser();
        courseUser.setCourseId(rs.getInt("id"));
        courseUser.setCourseName(rs.getString("name"));
        courseUser.setUserId(rs.getInt("user_id"));
        courseUser.setUserName(rs.getString("user_name"));
        courseUser.setEnrolled(rs.getString("enrolled").equalsIgnoreCase("Y"));
        return courseUser;
    }
}
