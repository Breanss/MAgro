package com.example.demo.controller;

import com.example.demo.entity.TypeCrop;
import com.example.demo.entity.User;
import com.example.demo.service.TypeCropService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class TypeCropController {

    @Autowired
    UserService userService;

    @Autowired
    TypeCropService typeCropService;

    @GetMapping({"/crops/typecrop"})
    @PreAuthorize("hasRole('User')")
    public ArrayList<TypeCrop> viewAllTypeCrops(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByUserName(userDetails.getUsername());
        return typeCropService.findAllTypeCrop(user);
    }
}
