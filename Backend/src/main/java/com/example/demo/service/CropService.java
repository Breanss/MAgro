package com.example.demo.service;

import com.example.demo.dao.CropDao;
import com.example.demo.entity.Crop;
import com.example.demo.entity.Season;
import com.example.demo.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CropService {
    @Autowired
    CropDao cropDao;

    public Crop addCrop(Crop crop, User user) {
        Crop add = new Crop();
        try {
            add = cropDao.save(crop);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed add crop");
        }
        return add;
    }

    public ArrayList<Crop> findBySeason(Season season, User user) {
        ArrayList<Crop> crops = new ArrayList<>();
        try {
            crops = cropDao.findBySeason(season);
        } catch (Exception e) {
            LoggerFactory.getLogger(FieldService.class).error(user.getName() + " failed get crops by season");
        }
        return crops;
    }
}
