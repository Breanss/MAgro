package com.example.demo.dao;

import com.example.demo.entity.Season;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface SeasonDao extends CrudRepository<Season, Long> {
    @Query("SELECT s  FROM Season s where s.user = ?1")
    ArrayList<Season> findByUser(User user);

    @Query("SELECT s FROM Season s WHERE s.seasonId=?1")
    Season findSeasonById(Long id);
}
