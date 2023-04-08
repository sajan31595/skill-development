package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.helper.UserValidationHelper;
import com.coaching.skilldevelopment.payload.CreateUserRequest;
import com.coaching.skilldevelopment.services.interfaces.MySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/api/my")
public class MyController {

    @Autowired
    private MySerivce myService;

    @Autowired
    UserValidationHelper userValidator;

    @GetMapping("/profile")
    public ResponseEntity<?> getUsers() {
        User user = myService.getUser();
        return ResponseEntity.ok(user);
    }

    @PutMapping("/profile")
    public ResponseEntity<?> updateUser(@RequestBody CreateUserRequest request) {
        User user = userValidator.getUpdatedUser(request);
        myService.updateUser(user);
        user = myService.getUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("/courses")
    public ResponseEntity<?> getCourses() {
        List<Course> courses = myService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/events")
    public ResponseEntity<?> getEvents() {
        List<Event> events = myService.getEvents();
        return ResponseEntity.ok(events);
    }
}
