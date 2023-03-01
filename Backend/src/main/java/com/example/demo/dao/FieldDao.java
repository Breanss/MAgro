package com.example.demo.dao;

import com.example.demo.entity.Field;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.ArrayList;

@Repository
public interface FieldDao extends CrudRepository<Field, Long> {
    @Query("SELECT f  FROM Field f where f.user = ?1 and f.fieldActive=true ")
    ArrayList<Field> finAllByUser(User user);

    @Query("SELECT f  FROM Field f where f.fieldName = ?1")
    ArrayList<Field> findFieldByName(String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Field f SET f.fieldArea=:area where f.fieldId = :id")
    void updateAreaCategoryPropertyFieldById(@Param("id") Long id, @Param("area") BigDecimal area);
}
