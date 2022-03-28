package com.example.cicd.controller;

import com.example.cicd.model.User;
import com.example.cicd.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TestRestController {

    @InjectMocks
    UserController userController;

    @Mock
    private UserService userService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetUserById() {
        User u = new User();
        u.setId(1);
        u.setName("Smartcar");
        when(userService.getById(1)).thenReturn(u);
        User user = userController.getUser(1);
        assertEquals("Smartcar", user.getName());
    }
}