package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.helper.UserValidationHelper;
import com.coaching.skilldevelopment.payload.JwtResponse;
import com.coaching.skilldevelopment.payload.LoginRequest;
import com.coaching.skilldevelopment.payload.RegisterUserRequest;
import com.coaching.skilldevelopment.security.jwt.BasicAuthenticationToken;
import com.coaching.skilldevelopment.security.jwt.JwtAuthManager;
import com.coaching.skilldevelopment.security.jwt.JwtUtils;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins="*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    JwtAuthManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserValidationHelper userValidator;

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        BasicAuthenticationToken authentication = (BasicAuthenticationToken) authenticationManager.authenticate(
                new BasicAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = (User) authentication.getUser();
        String jwt = jwtUtils.generateJwtToken(user);
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                new ArrayList<String>()));
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterUserRequest request){
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
}
