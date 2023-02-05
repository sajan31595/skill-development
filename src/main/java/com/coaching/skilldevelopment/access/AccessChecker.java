package com.coaching.skilldevelopment.access;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.security.jwt.BasicAuthenticationToken;
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

    public void canAccessCourses() throws AuthenticationException {
        BasicAuthenticationToken auth =
                (BasicAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            User user = auth.getUser();
            List<Role.RoleType> roles = userService.getRoles(Integer.parseInt(user.getId()));
            if (roles.contains(Role.RoleType.ADMIN) || roles.contains(Role.RoleType
                    .INSTRUCTOR)) return;
        }
        throw new AuthenticationException();
    }
}
