package com.example.demo.controller;

import com.example.demo.entity.*;
import com.example.demo.service.CropService;
import com.example.demo.service.FieldService;
import com.example.demo.service.SeasonService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Objects;


@RestController
public class SeasonController {

    @Autowired
    private UserService userService;

    @Autowired
    private SeasonService seasonService;
    @Autowired
    private FieldService fieldService;
    @Autowired
    private CropService cropService;


    @GetMapping({"/seasons"})
    @PreAuthorize("hasRole('User')")
    public ArrayList<Season> viewSeasonsUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUserName(userDetails.getUsername());

        return seasonService.findSeasonByUser(user);
    }

    @GetMapping({"/season/{id}"})
    @PreAuthorize("hasRole('User')")
    public Season viewSeasonById(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUserName(userDetails.getUsername());

        return seasonService.getSeasonById(id,user);
    }

    @PostMapping({"/seasons/addseason"})
    @PreAuthorize("hasRole('User')")
    public Season addSeason(@RequestBody Season season, @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUserName(userDetails.getUsername());
        season.setUser(user);

        ArrayList<Season> seasons = seasonService.findSeasonByUser(user);

        boolean exist = false;
        for (Season s : seasons) {
            if (Objects.equals(s.getYear(), season.getYear())) {
                exist = true;
                break;
            }
        }

        Season seasonAdd;

        if (!exist) {
            seasonAdd = seasonService.addSeason(season, user);
            ArrayList<Field> fields = fieldService.getFieldsUser(user);
            TypeCrop typeCrop = new TypeCrop();
            typeCrop.setName("Zdecyduję później");
            typeCrop.setTypeId(1L);

            for (Field field : fields) {
                Crop crop = new Crop();
                crop.setSeason(seasonAdd);
                crop.setField(field);
                crop.setTypeCrop(typeCrop);
                cropService.addCrop(crop, user);
            }

            return seasonAdd;
        }
        return null;
    }

}
