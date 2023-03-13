package com.example.demo.dao;

import com.example.demo.entity.Crop;
import com.example.demo.entity.Season;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface CropDao extends CrudRepository<Crop, Long> {

    @Query("SELECT c  FROM Crop c where c.season = ?1")
    ArrayList<Crop> findBySeason(Season season);

}
