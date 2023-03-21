package com.example.demo.dao;

import com.example.demo.entity.TypeCrop;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface TypeCropDao extends CrudRepository<TypeCrop,Long> {
    @Query("SELECT t FROM TypeCrop t")
    ArrayList<TypeCrop> findAllTypeCrop();

}
