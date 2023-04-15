package com.coaching.skilldevelopment.access;

import com.coaching.skilldevelopment.dto.Course;
import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.security.jwt.BasicAuthenticationToken;
import com.coaching.skilldevelopment.services.interfaces.ICourseService;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.naming.AuthenticationException;
import java.util.List;

@Component
public class AccessChecker {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICourseService courseService;

    public void canAccessUsers() throws AuthenticationException {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = auth.getUser();
            List<Role.RoleType> roles = userService.getRoles(Integer.parseInt(user.getId()));
            if(roles.contains(Role.RoleType.ADMIN)) return;
        }
        throw new AuthenticationException();
    }

    public void canAccessCourses(int courseId) throws AuthenticationException {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = auth.getUser();
            List<Role.RoleType> roles = userService.getRoles(Integer.parseInt(user.getId()));
            if (roles.contains(Role.RoleType.ADMIN)) return;
            else if(courseId>0 && roles.contains(Role.RoleType.INSTRUCTOR)) {
                List<Course> courses = courseService.getCoursesForInstructor(user.getId());
                courses.stream()
                        .filter(course -> course.getId() == courseId)
                        .findAny()
                        .orElseThrow(() -> new AuthenticationException());
            }
        }
        throw new AuthenticationException();
    }

    public User getCurrentUser() {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        return auth != null ? auth.getUser():null;
    }
}
