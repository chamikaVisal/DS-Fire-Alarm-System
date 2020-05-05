package com.sensor.fir_alarm_sensor.service;

import com.sensor.fir_alarm_sensor.entity.User_;
import com.sensor.fir_alarm_sensor.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository urepo;

//    //add user details from desktop app
//    public User_ addUser(User_ user){
//        return urepo.save(user);
//    }

//    //get user details to desktop app
//    public User_ getUser(){
//        return urepo.findAll();
//    }
}
