package com.example.demo.service;

import com.example.demo.dao.FieldDao;
import com.example.demo.entity.Field;
import com.example.demo.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;


@Service
public class FieldService {

    @Autowired
    private FieldDao fieldDao;

    public ArrayList<Field> getFieldsUser(User user) {
        ArrayList<Field> fields = new ArrayList<>();

        try {
            fields = fieldDao.findByUser(user);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed to fetch field data");
        }

        return fields;
    }

    public void deleteFieldById(Long id, User user) {
        try {
            fieldDao.deleteById(id);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed delete field");
        }
    }

    public ArrayList<Field> getFieldByName(String name, User user) {
        ArrayList<Field> fields = new ArrayList<>();
        try {
            fields = fieldDao.findByName(name);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed get field by name");
        }
        return fields;
    }

    public Field addField(Field field, User user) {
        Field add = new Field();
        try {
            add = fieldDao.save(field);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed add field");
        }
        return add;
    }

    public void editFieldAreaCategoryPropertyById(Long id, BigDecimal area, User user) {
        try {
            fieldDao.updateAreaCategoryPropertyFieldById(id, area);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed edit field");
        }
    }


}
