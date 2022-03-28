package com.example.cicd.controller;

import com.example.cicd.model.User;
import com.example.cicd.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/vehicles/")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "user/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Integer id) {
        User user = userService.getById(id);
        return user;
    }
}
