package com.example.demo.service;

import com.example.demo.dao.FieldDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.dao.UserDao;
import com.example.demo.entity.Field;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Service
public class InitData {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private FieldDao fieldDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private User user;

    @PostConstruct
    public void initRoleAndUser() {

        Role adminRole = new Role();
        adminRole.setRoleName("Admin");
        adminRole.setRoleDescription("Admin role");
        roleDao.save(adminRole);

        Role userRole = new Role();
        userRole.setRoleName("User");
        userRole.setRoleDescription("Default role for newly created record");
        roleDao.save(userRole);

        user = new User();
        user.setId(1L);
        user.setUserName("test");
        user.setUserPassword(passwordEncoder.encode("test"));
        user.setUserFirstName("Test");
        user.setUserLastName("Test");
        user.setUserEmail("test@o2.pl");
        user.setUserTelephonNumber("123456789");
        Set<Role> userRoles = new HashSet<>();
        userRoles.add(userRole);
        user.setRole(userRoles);
        userDao.save(user);
        initFieldUser();
    }



    public void initFieldUser() {
        Field field = new Field();
        field.setFieldId(1L);
        field.setFieldNameField("Pole");
        field.setFieldActive(true);
        field.setFieldArea(new BigDecimal("20.24"));
        field.setFieldProperty("Dzierżawa");
        field.setFieldNumberRegistration("2343A");
        field.setFieldArgonomicCategory("Lekka");
        field.setUser(user);
        fieldDao.save(field);
        field.setFieldId(2L);
        fieldDao.save(field);
        field.setFieldId(3L);
        fieldDao.save(field);

    }

}
