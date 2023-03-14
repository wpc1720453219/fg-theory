package com.wpcwl.myjavadebug.controller;

import com.wpcwl.myjavadebug.entity.User;
import com.wpcwl.myjavadebug.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TestController {

    @Autowired
    private UserService userService;

    @GetMapping("/demo")
    public User demo() {
        User user = userService.findAll();
        int a = 8, b = 0;
        System.out.println("Ex: " + (a / b));
        return user;
    }
}
