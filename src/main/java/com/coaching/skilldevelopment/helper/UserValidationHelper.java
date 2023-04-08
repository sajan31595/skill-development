package com.coaching.skilldevelopment.helper;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.payload.RegisterUserRequest;
import com.coaching.skilldevelopment.security.jwt.EncryptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class UserValidationHelper {

    @Autowired
    private EncryptionManager passwordManager;

    public boolean isValidRequest(RegisterUserRequest request) {
        if(!request.getPassword().equals(request.getConfirmPassword())) {
            return false;
        }
        return true;
    }

    public User getUser(RegisterUserRequest request){
        User user = getBasicUserDetails(request);
        user.setPassword(passwordManager.encode(request.getPassword()));
        return user;
    }

    public User getUpdatedUser(RegisterUserRequest request) {
        User user = getBasicUserDetails(request);
        return user;
    }

    private User getBasicUserDetails(RegisterUserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setSex(User.SEX.valueOf(request.getSex()));
        user.setPhoneNumber(request.getPhone());
        try {user.setBirthDate(new SimpleDateFormat("yyyy-mm-dd").parse(request.getBirthDate()));}
        catch(ParseException pe){}
        return user;
    }

}
