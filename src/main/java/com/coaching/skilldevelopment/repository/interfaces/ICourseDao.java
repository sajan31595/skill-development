package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;

import java.util.List;

public interface ICourseDao {

    List<Course> findAll();

    void createCourse(Course course);

    List<Course> getCoursesForInstructor(String userId);

    boolean isUserEnrolled(int userId, int courseId);

    void addUserToCourse(int courseId, int userId);

    void addEvent(Event event);

    List<Event> getEvents(int courseId);
}
