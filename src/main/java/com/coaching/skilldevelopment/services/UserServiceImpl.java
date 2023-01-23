package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.repository.UserDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private UserDaoImpl userDao;

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
    public void saveUser(User user) {
        userDao.create(user);
    }
}
