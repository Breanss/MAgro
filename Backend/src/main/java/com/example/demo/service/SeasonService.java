package com.example.demo.service;


import com.example.demo.dao.SeasonDao;
import com.example.demo.entity.Season;
import com.example.demo.entity.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SeasonService {
    @Autowired
    SeasonDao seasonDao;

    public ArrayList<Season> findSeasonByUser(User user) {
        ArrayList<Season> seasons = new ArrayList<>();

        try {
            seasons = seasonDao.findByUser(user);
        } catch (Exception e) {
            LoggerFactory.getLogger(SeasonService.class).error(user.getName() + " failed to fetch field data");
        }

        return seasons;
    }

    public Season addSeason(Season season, User user) {
        Season add = new Season();
        try {
            add = seasonDao.save(season);
        } catch (Exception e) {
            LoggerFactory.getLogger(SeasonService.class).error(user.getName() + " failed add season");
        }
        return add;
    }

    public Season getSeasonById(Long id, User user) {
        Season season = new Season();
        try {
            season = seasonDao.findSeasonById(id);
        } catch (Exception e) {
            LoggerFactory.getLogger(SeasonService.class).error(user.getName() + " failed add season");
        }
        return season;
    }
}
