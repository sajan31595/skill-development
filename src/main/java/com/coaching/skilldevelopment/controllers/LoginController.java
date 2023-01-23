package com.coaching.skilldevelopment.controllers;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.payload.JwtResponse;
import com.coaching.skilldevelopment.payload.LoginRequest;
import com.coaching.skilldevelopment.security.jwt.BasicAuthenticationToken;
import com.coaching.skilldevelopment.security.jwt.JwtAuthManager;
import com.coaching.skilldevelopment.security.jwt.JwtUtils;
import com.coaching.skilldevelopment.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin(origins="*", maxAge = 3600)
@Controller
@RequestMapping("/api/auth")
public class LoginController {
    @Autowired
    JwtAuthManager authenticationManager;

    @Autowired
    private IUserService userService;

    @Autowired
    JwtUtils jwtUtils;

    // handler method to handle home page request
    @GetMapping("/index")
    public String home(){
        return "index";
    }

    // handler method to handle login request
    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        BasicAuthenticationToken authentication = (BasicAuthenticationToken) authenticationManager.authenticate(
                new BasicAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        User user = (User) authentication.getDetails();
        String jwt = jwtUtils.generateJwtToken(user);
        return ResponseEntity.ok(new JwtResponse(jwt,
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                new ArrayList<String>()));
    }

    // handler method to handle user registration form request
    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
}
