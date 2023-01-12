package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.services.IUserService;
import com.coaching.skilldevelopment.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/users")
    public ResponseEntity<?> getUsers() {

        return (ResponseEntity<?>) userService.getUsers();
    }
}
