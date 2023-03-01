package com.example.demo.service;

import com.example.demo.dao.FieldDao;
import com.example.demo.entity.Field;
import com.example.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.math.BigDecimal;
import java.util.ArrayList;


@Service
public class FieldService {

    @Autowired
    private FieldDao fieldDao;

    public ArrayList<Field> getFieldUser(User user){
       return fieldDao.finAllByUser(user);
    }
    public void deleteFieldById(Long id){fieldDao.deleteById(id);}

    public ArrayList<Field> getFieldByName(String name){return fieldDao.findFieldByName(name);}
    public Field addField(Field field){return fieldDao.save(field);}

    public void editFieldAreaCategoryPropertyById(Long id, BigDecimal area){
        fieldDao.updateAreaCategoryPropertyFieldById(id,area);
    }


}
