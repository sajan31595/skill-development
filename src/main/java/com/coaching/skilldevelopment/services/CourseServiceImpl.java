package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.Course;
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
}
