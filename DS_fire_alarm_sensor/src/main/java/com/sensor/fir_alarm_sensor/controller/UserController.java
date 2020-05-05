package com.sensor.fir_alarm_sensor.controller;

import com.sensor.fir_alarm_sensor.entity.User_;
import com.sensor.fir_alarm_sensor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    public UserService uservice;

    //add admin details from desktop client to the database
//    @PostMapping("/Login")
//    public User_ AdminLogin(User_ user){
//        return uservice.addUser(user);
//    }
}
