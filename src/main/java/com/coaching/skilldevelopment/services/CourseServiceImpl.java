package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;
import com.coaching.skilldevelopment.repository.interfaces.ICourseDao;
import com.coaching.skilldevelopment.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDao courseDao;

    @Override
    public List<Course> getCourses() {
        List<Course> courses = courseDao.findAll();
        return courses;
    }

    @Override
    public Course getCourse(int courseId) {
        return courseDao.getCourse(courseId);
    }

    @Override
    public void updateCourse(int courseId, Course course) {
        courseDao.updateCourse(courseId, course);
    }

    @Override
    public void deleteCourse(int courseId) {
        courseDao.deleteCourse(courseId);
    }

    @Override
    public void createCourse(Course course) {
        courseDao.createCourse(course);
    }

    @Override
    public List<Course> getCoursesForInstructor(String userId){
        return courseDao.getCoursesForInstructor(userId);
    }

    @Override
    public void addUsersToCourse(int courseId, List<Integer> userIds){
        for(Integer user: userIds) {
            if(!courseDao.isUserEnrolled(user, courseId))
                courseDao.addUserToCourse(courseId, user);
            // TODO
            //send notification.
        }
    }

    @Override
    public List<Course> getEnrolledCourses(int userId) {
        return courseDao.getEnrolledCourses(userId);
    }

    @Override
    public void addEvent(Event event) {
        courseDao.addEvent(event);
    }

    @Override
    public List<Event> getEvents(int courseId){
        return courseDao.getEvents(courseId);
    }

    @Override
    public List<Event> getEvents(){
        return courseDao.getEvents();
    }

    @Override
    public Event getEvent(int eventId){
        return courseDao.getEvent(eventId);
    }

    @Override
    public void updateEvent(int eventId, Event event){
        courseDao.updateEvent(eventId, event);
    }

    @Override
    public void deleteEvent(int eventId){
        courseDao.deleteEvent(eventId);
    }
}
