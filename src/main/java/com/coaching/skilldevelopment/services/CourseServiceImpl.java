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
    public void addEvent(Event event) {
        courseDao.addEvent(event);
    }

    @Override
    public List<Event> getEvents(int courseId){
        return courseDao.getEvents(courseId);
    }
}
