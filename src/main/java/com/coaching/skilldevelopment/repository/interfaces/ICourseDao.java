package com.coaching.skilldevelopment.repository.interfaces;

import com.coaching.skilldevelopment.dto.Course;

import java.util.List;

public interface ICourseDao {

    List<Course> findAll();

    void createCourse(Course course);
}
