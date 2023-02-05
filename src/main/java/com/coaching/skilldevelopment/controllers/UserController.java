package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.access.AccessChecker;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.payload.UserRoleRequest;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.naming.AuthenticationException;
import java.util.List;

@Controller
@CrossOrigin(origins="*", maxAge = 3600)
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private IUserService userService;

    @Autowired
    private AccessChecker access;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() throws AuthenticationException {
        access.canAccessUsers();
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/addUsersToRole")
    public ResponseEntity<?> addUserToRoles(UserRoleRequest userRoleRequest)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessUsers();
        userService.addUserToRoles(userRoleRequest.getUserToRoles());
        return ResponseEntity.ok("");
    }
}
