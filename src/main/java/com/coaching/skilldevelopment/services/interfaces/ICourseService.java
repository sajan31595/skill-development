package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;

import java.util.List;

public interface ICourseService {

    List<Course> getCourses();

    void createCourse(Course course);
    
    Course getCourse(int courseId);

    void updateCourse(int courseId, Course course);
    
    void deleteCourse(int courseId);

    List<Course> getCoursesForInstructor(String userId);

    void addUsersToCourse(int courseId, List<Integer> userIds);

    List<Course> getEnrolledCourses(int userId);

    void addEvent(Event event);

    List<Event> getEvents(int courseId);

    List<Event> getEvents();

    Event getEvent(int eventId);

    void updateEvent(int eventId, Event event);

    void deleteEvent(int eventId);
}
