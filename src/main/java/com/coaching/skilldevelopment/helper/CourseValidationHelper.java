package com.coaching.skilldevelopment.helper;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.payload.CreateCourseRequest;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class CourseValidationHelper {

    public Course getCourse(CreateCourseRequest request) {
        Course course = new Course();
        course.setName(request.getName());
        course.setDescription(request.getDescription());
        course.setAuthor_id(request.getAuthor_id());
        course.setType(request.getType());
        course.setGroup_link(request.getGroup_link());
        try {course.setStart_date(new SimpleDateFormat("dd-mm-yyyy").parse(request.getStart_date()));}
        catch(ParseException pe){}
        return course;
    }
}
