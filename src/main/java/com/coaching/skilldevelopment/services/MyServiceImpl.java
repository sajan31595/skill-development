package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;
import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.security.jwt.BasicAuthenticationToken;
import com.coaching.skilldevelopment.services.interfaces.ICourseService;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import com.coaching.skilldevelopment.services.interfaces.MySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyServiceImpl implements MySerivce {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    @Override
    public User getUser(){
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        User user = null;
        if (auth != null) {
            user = auth.getUser();
            if(user != null && !user.getId().isEmpty()) {
                user = userService.getUserById(Integer.parseInt(user.getId()));
                List<Role.RoleType> roles = userService.getRoles(Integer.parseInt(user.getId()));
                user.setRoles(roles);
            }
        }
        return user;
    }

    @Override
    public void updateUser(User user) {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getUser()!=null) {
            userService.updateUser(Integer.parseInt(auth.getUser().getId()), user);
        }
    }

    public List<Course> getCourses() {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getUser()!=null) {
            return courseService.getEnrolledCourses(Integer.parseInt(auth.getUser().getId()));
        }
        return null;
    }

    public List<Event> getEvents() {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.getUser()!=null) {
            List<Course> courses = courseService.getEnrolledCourses(Integer.parseInt(auth.getUser().getId()));
            List<Event> events = new ArrayList<Event>();
            courses.forEach(course -> {
                List<Event> courseEvents = courseService.getEvents(course.getId());
                if(courseEvents!=null && courseEvents.size()>0)
                    events.addAll(courseEvents);
            });
            return events;
        }
        return null;
    }
}
