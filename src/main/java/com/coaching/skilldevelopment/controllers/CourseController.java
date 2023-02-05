package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.access.AccessChecker;
import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.helper.CourseValidationHelper;
import com.coaching.skilldevelopment.payload.CreateCourseRequest;
import com.coaching.skilldevelopment.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import java.util.List;

@Controller
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private AccessChecker access;

    @Autowired
    private CourseValidationHelper helper;


    @GetMapping("/")
    public ResponseEntity<?> getCourses() throws AuthenticationException {
        access.canAccessCourses();
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest request)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessCourses();
        Course course = helper.getCourse(request);
        courseService.createCourse(course);
        return ResponseEntity.ok("");
    }


}
