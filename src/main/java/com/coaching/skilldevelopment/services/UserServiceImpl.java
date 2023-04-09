package com.coaching.skilldevelopment.services;

import com.coaching.skilldevelopment.dto.Role;
import com.coaching.skilldevelopment.dto.User;
import com.coaching.skilldevelopment.exception.InvalidRequestException;
import com.coaching.skilldevelopment.repository.interfaces.IRoleDao;
import com.coaching.skilldevelopment.repository.interfaces.IUserDao;
import com.coaching.skilldevelopment.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    public User getUserById(int id) {
        User user;
        try{
            user = userDao.findUserById(id);
        } catch(Exception ex){
            throw new UsernameNotFoundException("User not present");
        }
        return user;
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

    @Override
    public void updateUser(int userId, User user) {
        userDao.updateUser(userId, user);
    }

    @Override
    public void deleteUser(int userId) {
        userDao.deleteUser(userId);
    }

    @Override
    public List<Role.RoleType> getRoles(int userId) {
        List<String> roles = userDao.getRoles(userId);
        List<Role.RoleType> updatedRoles = new ArrayList<Role.RoleType>();
        for(String role: roles){
            if(Role.RoleType.valueOf(role)!=null){
                updatedRoles.add(Role.RoleType.valueOf(role));
            }
        }
        return updatedRoles;
    }

    public List<Role> getRoles() {
        List<Role> roles = roleDao.getRoles();
        for (Role role: roles) {
            List<User> users = roleDao.getUsers(role.getRoleId());
            role.setMembers(users);
        }
        return roles;
    }

    @Override
    public void addUserToRoles(int roleId, List<Integer> userIds) throws InvalidRequestException {
        //validate if the roleIds are valid.
        Role role = roleDao.getRole(roleId);
        if(role == null) throw new InvalidRequestException("Invalid role");

        for(int userId: userIds){
            User user = userDao.findUserById(userId);
            if(user==null) throw new InvalidRequestException("Invalid some of users");
            if(!roleDao.isUserExistInRole(roleId, userId)){
                //Sending the request to Dao
                userDao.addUserToRoles(userId, roleId);
            }
        }

    }
}
