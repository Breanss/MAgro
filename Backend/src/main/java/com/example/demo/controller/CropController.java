package com.example.demo.controller;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Season;
import com.example.demo.entity.User;
import com.example.demo.service.CropService;
import com.example.demo.service.SeasonService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;


@RestController
public class CropController {

    @Autowired
    UserService userService;

    @Autowired
    SeasonService seasonService;

    @Autowired
    CropService cropService;

    @GetMapping({"/crops/totalarea"})
    @PreAuthorize("hasRole('User')")
    public HashMap<Long, BigDecimal> totalAreaCropsForSeasons(@AuthenticationPrincipal UserDetails userDetails) {
        HashMap<Long, BigDecimal> totalArea = new HashMap<>();

        User user = userService.getUserByUserName(userDetails.getUsername());
        ArrayList<Season> seasons = seasonService.findSeasonByUser(user);

        for (Season season : seasons) {
            ArrayList<Crop> crops = cropService.findBySeason(season, user);
            BigDecimal sum = new BigDecimal(0);
            for (Crop crop : crops) {
                sum = sum.add(crop.getField().getArea());
            }
            totalArea.put(season.getSeasonId(), sum);
        }

        return totalArea;
    }

    @GetMapping({"/crops/whetheralldeclared"})
    @PreAuthorize("hasRole('User')")
    public HashMap<Long, Boolean> whetherAllCropsAreDeclaredForSeasons(@AuthenticationPrincipal UserDetails userDetails) {
        HashMap<Long, Boolean> totalArea = new HashMap<>();

        User user = userService.getUserByUserName(userDetails.getUsername());
        ArrayList<Season> seasons = seasonService.findSeasonByUser(user);

        for (Season season : seasons) {
            ArrayList<Crop> crops = cropService.findBySeason(season, user);
            boolean tmp = true;
            for (Crop crop : crops) {
                if (crop.getTypeCrop() == null || crop.getTypeCrop().getTypeId() == 1) {
                    tmp = false;
                    break;
                }
            }
            totalArea.put(season.getSeasonId(), tmp);
        }

        return totalArea;
    }

}
