package com.example.demo.service;

import com.example.demo.dao.TypeCropDao;
import com.example.demo.entity.TypeCrop;
import com.example.demo.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class TypeCropService {

    @Autowired
    TypeCropDao typeCropDao;

    public ArrayList<TypeCrop> findAllTypeCrop(User user) {
        ArrayList<TypeCrop> arrayList = new ArrayList<>();

        try {
            arrayList = typeCropDao.findAllTypeCrop();
        } catch (Exception e) {
            LoggerFactory.getLogger(SeasonService.class).error(user.getName() + " failed find typecrops");
        }
        return arrayList;
    }
}
