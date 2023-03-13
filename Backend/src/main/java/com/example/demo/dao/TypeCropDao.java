package com.example.demo.dao;

import com.example.demo.entity.TypeCrop;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCropDao extends CrudRepository<TypeCrop,Long> {
}
