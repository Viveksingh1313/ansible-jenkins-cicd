package com.example.cicd.service;

import com.example.cicd.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    User user;

    public User getById(Integer id) {
        user.setId(id);
        user.setName("Smartcar");
        return user;
    }
}
