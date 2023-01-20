package com.coaching.skilldevelopment.security.jwt;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthManager {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtutils;

    public User authenticate(String username, String password) throws AuthenticationException {
        User user = userService.findByEmail(username);
        return user;
    }
}
