package com.coaching.skilldevelopment.security.jwt;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthManager implements AuthenticationManager {

    @Autowired
    private IUserService userService;

    @Autowired
    private JwtUtils jwtutils;

    @Autowired
    private EncryptionManager encryptionManager;

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        User user = userService.findByUsername(String.valueOf(authentication.getPrincipal()));
        if(encryptionManager.matches(String.valueOf(authentication.getCredentials()), user.getPassword())) {
            BasicAuthenticationToken basicAuthenticationToken = new BasicAuthenticationToken(user);
            basicAuthenticationToken.setAuthenticated(true);
            basicAuthenticationToken.setUser(user);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return basicAuthenticationToken;
        } else throw new BadCredentialsException("Invalid credential");
    }
}
