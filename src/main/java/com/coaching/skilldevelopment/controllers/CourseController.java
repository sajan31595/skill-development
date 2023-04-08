package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.access.AccessChecker;
import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.helper.CourseValidationHelper;
import com.coaching.skilldevelopment.payload.CourseEventRequest;
import com.coaching.skilldevelopment.payload.CourseUsersRequest;
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
        access.canAccessCourses(0);
        List<Course> courses = courseService.getCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable int courseId) throws AuthenticationException {
        access.canAccessCourses(0);
        Course course = courseService.getCourse(courseId);
        return ResponseEntity.ok(course);
    }

    @PostMapping("/")
    public ResponseEntity<?> createCourse(@RequestBody CreateCourseRequest request)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessCourses(0);
        Course course = helper.getCourse(request);
        courseService.createCourse(course);
        return ResponseEntity.ok("");
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable int courseId,@RequestBody CreateCourseRequest request)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessCourses(0);
        Course course = helper.getCourse(request);
        courseService.updateCourse(courseId, course);
        course = courseService.getCourse(courseId);
        return ResponseEntity.ok(course);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable int courseId)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessCourses(0);
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok("Course Deleted");
    }

    @PostMapping("/addUsersToCourse")
    public ResponseEntity<?> addUsersToCourse(@RequestBody CourseUsersRequest request)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessCourses(request.getCourseId());
        courseService.addUsersToCourse(request.getCourseId(), request.getUserIds());
        return ResponseEntity.ok("");
    }

    @GetMapping("/courseEvents")
    public ResponseEntity<?> getEvents(int courseId) throws AuthenticationException {
        access.canAccessCourses(courseId);
        List<Event> events = courseService.getEvents(courseId);
        return ResponseEntity.ok(events);
    }

    @PostMapping("/addEvent")
    public ResponseEntity<?> addCourseEvents(@RequestBody CourseEventRequest request)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessCourses(request.getCourseId());
        courseService.addEvent(helper.getEvent(request));
        return ResponseEntity.ok("");
    }

    @GetMapping("/events")
    public ResponseEntity<?> getEvents() throws AuthenticationException {
        access.canAccessCourses(0);
        List<Event> events = courseService.getEvents();
        return ResponseEntity.ok(events);
    }

    @GetMapping("/events/{eventId}")
    public ResponseEntity<?> getEvent(@PathVariable int eventId) throws AuthenticationException {
        access.canAccessCourses(0);
        Event event = courseService.getEvent(eventId);
        return ResponseEntity.ok(event);
    }

    @PutMapping("/events/{eventId}")
    public ResponseEntity<?> updateEvent(@PathVariable int eventId, @RequestBody CourseEventRequest request)
            throws AuthenticationException {
        access.canAccessCourses(0);
        courseService.updateEvent(eventId, helper.getEvent(request));
        Event event = courseService.getEvent(eventId);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/events/{eventId}")
    public ResponseEntity<?> deleteEvent(@PathVariable int eventId) throws AuthenticationException {
        access.canAccessCourses(0);
        courseService.deleteEvent(eventId);
        return ResponseEntity.ok("Event deleted");
    }
}
