package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;
import com.coaching.skilldevelopment.dto.rowmapper.CourseRowMapper;
import com.coaching.skilldevelopment.dto.rowmapper.EventRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.ICourseDao;
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

    @Override
    @Transactional(readOnly = true)
    public Course getCourse(int courseId) {
        String sql = "select * from courses where ID = "+ courseId;
        Course course = null;
        try{
            course = jdbcTemplate.queryForObject(sql,new CourseRowMapper());
        }catch(Exception ex) {}
        return course;
    }

    @Override
    @Transactional
    public void updateCourse(int courseId, Course course) {
        String sql = "UPDATE COURSES SET " +
                "NAME =?, DESCRIPTION =?, TYPE=?, AUTHOR_ID=?, CREATED_BY=?, GROUP_LINK =?, " +
                "MODIFIED_ON =CURRENT_DATE, START_DATE =?, STATUS =? " +
                "WHERE id=?";
        try{
            jdbcTemplate.update(sql,
                    course.getName(),
                    course.getDescription(),
                    course.getType(),
                    course.getAuthor_id(),
                    course.getCreated_by(),
                    course.getGroup_link(),
                    course.getStart_date(),
                    "AC",
                    courseId);
        } catch (Exception ex){
        }
    }

    @Override
    @Transactional()
    public void deleteCourse(int courseId) {
        try{
            jdbcTemplate.update("DELETE FROM COURSES WHERE id=?", courseId);
        }catch (Exception ex){ }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getCoursesForInstructor(String userId) {
        int id = Integer.parseInt(userId);
        final String sql = "select * from courses where author_id = ? or created_by =?";
        List<Course> courses = null;
        try {
            courses = jdbcTemplate.query(sql, new Object[]{id, id},
                    new int[]{Types.INTEGER, Types.INTEGER}, new CourseRowMapper());
        }
        catch(Exception ex) {
            return null;
        }
        return courses;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean isUserEnrolled(int userId, int courseId) {
        long enrolmentId = 0;
        String sql = "select id from enrolment where user_id=? and course_id=?";
        try {
            enrolmentId = jdbcTemplate.queryForObject(sql, new Object[]{userId, courseId},
                    new int[]{Types.INTEGER, Types.INTEGER}, Long.class);
            return enrolmentId>0;
        }
        catch(Exception ex) {
            return false;
        }
    }

    @Override
    @Transactional
    public void addUserToCourse(int courseId, int userId){
        final String sql = "INSERT INTO ENROLMENTS(ID, USER_ID, COURSE_ID, CREATED_ON, STATUS )" +
                " VALUES (nextval('enrolments_seq'), ?, ?, CURRENT_DATE, 'AC')";
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, userId);
                    ps.setInt(2, courseId);
                    return ps;
                }
            }, holder);
        }
        catch(Exception ex) {
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> getEnrolledCourses(int userId) {
        String sql = "select c.* from enrolments e " +
                "left join courses c on c.id = e.course_id and user_id = "+ userId;
        List<Course> courses = null;
        try{
            courses = jdbcTemplate.query(sql,new CourseRowMapper());
        }catch(Exception ex) {}
        return courses;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(int courseId) {
        List<Event> events = null;
        String sql = "select * from course_events where course_id=" +courseId;
        try {
            events = jdbcTemplate.query(sql, new EventRowMapper());
        }
        catch(Exception ex) {
            return null;
        }
        return events;
    }

    @Override
    @Transactional
    public void addEvent(Event event){
        final String sql = "insert into course_events(ID, COURSE_ID, EVENT_TYPE, EVENT_NAME, EVENT_DESCRIPTION, EVENT_DATE, STATUS )" +
                " VALUES (nextval('course_events_seq'), ?, ?, ?, ?, ?, 'AC')";
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setInt(1, event.getCourseId());
                    ps.setString(2, event.getEventType());
                    ps.setString(3, event.getEventName());
                    ps.setString(4, event.getEventDescription());
                    ps.setDate(5, new java.sql.Date(event.getEventDate().getTime()));
                    return ps;
                }
            }, holder);
        } catch (Exception ex) {
            System.out.println("Error:"+ ex);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Event> getEvents(){
        List<Event> events = null;
        String sql = "select * from course_events where status='AC'";
        try {
            events = jdbcTemplate.query(sql, new EventRowMapper());
        }
        catch(Exception ex) {
            return null;
        }
        return events;
    }

    @Override
    @Transactional(readOnly = true)
    public Event getEvent(int eventId){
        Event event = null;
        String sql = "select * from course_events where status='AC'";
        try {
            event = jdbcTemplate.queryForObject(sql, new EventRowMapper());
        }
        catch(Exception ex) {
            return null;
        }
        return event;
    }

    @Override
    @Transactional
    public void updateEvent(int eventId, Event event){
        String sql = "UPDATE course_events SET COURSE_ID =?, EVENT_TYPE =?, EVENT_NAME=?, EVENT_DESCRIPTION=?, EVENT_DATE=?, STATUS =? WHERE id=?";
        try{
            jdbcTemplate.update(sql,
                    event.getCourseId(),
                    event.getEventType(),
                    event.getEventName(),
                    event.getEventDescription(),
                    event.getEventDate(),
                    "AC",
                    eventId);
        } catch (Exception ex){
        }
    }

    @Override
    @Transactional
    public void deleteEvent(int eventId){
        try{
            jdbcTemplate.update("DELETE FROM course_events WHERE id=?", eventId);
        }catch (Exception ex){ }
    }
}
