package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.repository.interfaces.IUserDao;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Override
    public List<User> getUsers() {
        return userDao.findAll();
    }

    @Override
    public User loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user;
        try{
            user = userDao.findUserByName(username);
        } catch(Exception ex){
            throw new UsernameNotFoundException("User not present");
        }
        return user;
    }

    @Override
    public User findByUsername(String name) {
        User user;
        try{
            user = userDao.findUserByName(name);
        } catch(Exception ex){
            throw new UsernameNotFoundException("User not present");
        }
        return user;
    }

    @Override
    public User findByEmail(String email){
        User user;
        try{
            user = userDao.findUserByName(email);
        } catch(Exception ex){
            throw new UsernameNotFoundException("User not present");
        }
        return user;
    }

    @Override
    public boolean isUsernameExists(String name) {
        try {
            return findByUsername(name) != null;
        } catch(UsernameNotFoundException ex){
            return false;
        }

    }

    @Override
    public User saveUser(User user) {
        return userDao.createUser(user);
    }
}
