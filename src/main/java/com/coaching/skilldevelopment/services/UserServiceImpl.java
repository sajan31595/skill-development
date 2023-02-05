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
import java.util.Map;
import java.util.stream.Collectors;

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

    @Override
    public void addUserToRoles(Map<Integer, List<Integer>> userToRoles) throws InvalidRequestException {
        for (Map.Entry<Integer, List<Integer>> userToRole : userToRoles.entrySet()) {
            int userId = userToRole.getKey();
            List<Integer> roleIds = userToRole.getValue();
            //validate if the userId is valid.
            User user = userDao.findUserById(userId);
            if(user==null) throw new InvalidRequestException();

            //validate if the roleIds are valid.
            List<Role> roles = roleDao.getRoles();
            List<Integer> roleIDsFromDB = roles.stream().map(Role::getRoleId).collect(Collectors.toList());
            List<Integer> invalidRoleIds = null;
            for (Integer roleId: roleIds) {
                if(!roleIDsFromDB.contains(roleId)) invalidRoleIds.add(roleId);
            }
            if(invalidRoleIds!=null) throw new InvalidRequestException();

            //Sending the request to Dao
            userDao.addUserToRoles(userId, roleIds);
        }
    }
}
