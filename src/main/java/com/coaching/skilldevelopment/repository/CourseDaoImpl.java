package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.rowmapper.CourseRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.ICourseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Repository
public class CourseDaoImpl implements ICourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {
        return jdbcTemplate.query("select * from courses",
                new CourseRowMapper());
    }

    @Override
    @Transactional
    public void createCourse(Course course) {
        {
            final String sql = "INSERT INTO COURSES(ID, NAME, DESCRIPTION, TYPE, AUTHOR_ID, CREATED_BY, GROUP_LINK, START_DATE, CREATED_ON, MODIFIED_ON, STATUS )" +
                    " VALUES (nextval('courses_seq'), ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, CURRENT_DATE, 'AC')";
            try {
                KeyHolder holder = new GeneratedKeyHolder();
                jdbcTemplate.update(new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                        ps.setString(1, course.getName());
                        ps.setString(2, course.getDescription());
                        ps.setString(3, course.getType());
                        ps.setInt(4, course.getAuthor_id());
                        ps.setInt(5, course.getCreated_by());
                        ps.setString(6, course.getGroup_link());
                        ps.setDate(7, new java.sql.Date(course.getStart_date().getTime()));
                        return ps;
                    }
                }, holder);
            } catch (Exception ex) {
                System.out.println("Error:"+ ex);
            }
        }
    }
}
