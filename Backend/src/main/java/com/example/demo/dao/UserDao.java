package com.example.demo.dao;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, String> {

    @Query("SELECT u  FROM User u where u.name = ?1")
    User findUserNameByName(String userName);

}
