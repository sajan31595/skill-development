package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.services.interfaces.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/api/classes")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @GetMapping("/")
    public ResponseEntity<?> getCourses() {
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCourses() {
        return ResponseEntity.ok("");
    }
}
