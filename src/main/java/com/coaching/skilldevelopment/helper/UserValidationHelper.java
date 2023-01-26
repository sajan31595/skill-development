package com.coaching.skilldevelopment.helper;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.payload.RegisterUserRequest;
import com.coaching.skilldevelopment.security.jwt.EncryptionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordManager.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        user.setAge(request.getAge());
        user.setSex(User.SEX.valueOf(request.getSex()));
        return user;
    }
}
