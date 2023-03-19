package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.access.AccessChecker;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.helper.UserValidationHelper;
import com.coaching.skilldevelopment.payload.CreateUserRequest;
import com.coaching.skilldevelopment.payload.UserRoleRequest;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    UserValidationHelper userValidator;

    @GetMapping("/")
    public ResponseEntity<?> getUsers() throws AuthenticationException {
        access.canAccessUsers();
        List<User> users = userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/")
    public ResponseEntity<?> createUser(@RequestBody CreateUserRequest request)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessUsers();
        if (!userValidator.isValidRequest(request)){
            return ResponseEntity.badRequest().body("Bad Request");
        }
        if (userService.isUsernameExists(request.getUsername())) {
            return ResponseEntity.badRequest().body("Username exists");
        }
        User user = userValidator.getUser(request);
        user = userService.saveUser(user);
        return ResponseEntity.ok("User Created");
    }

    @PostMapping("/addUsersToRole")
    public ResponseEntity<?> addUserToRoles(@RequestBody UserRoleRequest userRoleRequest)
            throws AuthenticationException, InvalidRequestException {
        access.canAccessUsers();
        userService.addUserToRoles(userRoleRequest.getRoleId(), userRoleRequest.getUserIds());
        return ResponseEntity.ok("");
    }
}
