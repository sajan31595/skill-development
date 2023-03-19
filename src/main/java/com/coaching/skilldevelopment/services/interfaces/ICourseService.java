package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;

import java.util.List;

public interface ICourseService {

    List<Course> getCourses();

    void createCourse(Course course);

    List<Course> getCoursesForInstructor(String userId);

    void addUsersToCourse(int courseId, List<Integer> userIds);

    void addEvent(Event event);

    List<Event> getEvents(int courseId);
}
