package com.coaching.skilldevelopment.services.interfaces;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Event;
import com.coaching.skilldevelopment.dto.User;

import java.util.List;

public interface MySerivce {

    User getUser();

    void updateUser(User user);

    List<Course> getCourses();

    List<Event> getEvents();
}
