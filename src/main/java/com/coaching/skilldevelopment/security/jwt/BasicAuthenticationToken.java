package com.coaching.skilldevelopment.security.jwt;

import com.coaching.skilldevelopment.dto.User;
import org.springframework.security.authentication.AbstractAuthenticationToken;

import java.util.ArrayList;

public class BasicAuthenticationToken extends AbstractAuthenticationToken {

    private User user;
    private String principal;
    private String credential;

    public BasicAuthenticationToken(String principal, String credentials) {
        super(new ArrayList<>());
        this.principal = principal;
        this.credential = credentials;
        this.setAuthenticated(true);
    }

    public BasicAuthenticationToken(User user) {
        super(new ArrayList<>());
        this.user = user;
        this.setAuthenticated(true);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public Object getCredentials() {
        return this.credential;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
