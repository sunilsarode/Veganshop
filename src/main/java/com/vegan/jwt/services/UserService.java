package com.vegan.jwt.services;

import com.vegan.jwt.dao.RoleDao;
import com.vegan.jwt.dao.UserDao;
import com.vegan.jwt.entity.Role;
import com.vegan.jwt.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {

    @Autowired
    RoleDao roleDao;

    @Autowired
    UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;
    public void initRolesAndUser(){
        Role adminRole =new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin Role");
        roleDao.save(adminRole);

        Role userRole=new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default Role");
        roleDao.save(userRole);

        User adminUser =new User();
        adminUser.setUserName("admin123");
        adminUser.setFirstName("admin");
        adminUser.setLastName("admin");
        adminUser.setUserPassword(getEncodedPassword("admin@pass"));


        Set<Role> adminRoles=new HashSet<>();
        adminRoles.add(adminRole);
        adminUser.setRole(adminRoles);
        userDao.save(adminUser);

//        User user =new User();
//        user.setUserName("sunil123");
//        user.setFirstName("sunil");
//        user.setLastName("sarode");
//        user.setUserPassword("sunil@pass");
//
//        Set<Role> userRoles=new HashSet<>();
//        userRoles.add(userRole);
//        user.setRole(userRoles);
//        userDao.save(user);

    }

    public User registerNewUser(User user) {
        Role role = roleDao.findById("User").get();//register user will get normal role
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(role);
        user.setRole(userRoles);
        user.setUserPassword(getEncodedPassword(user.getUserPassword()));

        return userDao.save(user);
    }

    public String getEncodedPassword(String password) {
        return passwordEncoder.encode(password);
    }
}
