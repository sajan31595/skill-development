package com.coaching.skilldevelopment.security.jwt;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CustomSHAManager extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword){
        return rawPassword.toString().equals(encodedPassword);
    }

}
