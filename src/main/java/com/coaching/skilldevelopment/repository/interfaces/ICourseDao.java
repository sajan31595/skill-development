package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;

import java.util.List;

public interface ICourseDao {

    List<Course> findAll();

    void createCourse(Course course);

    Course getCourse(int courseId);

    void updateCourse(int courseId, Course course);

    void deleteCourse(int courseId);

    List<Course> getCoursesForInstructor(String userId);

    boolean isUserEnrolled(int userId, int courseId);

    void addUserToCourse(int courseId, int userId);

    List<Course> getEnrolledCourses(int userId);

    void addEvent(Event event);

    List<Event> getEvents(int courseId);

    List<Event> getEvents();

    Event getEvent(int eventId);

    void updateEvent(int eventId, Event event);

    void deleteEvent(int eventId);
}
