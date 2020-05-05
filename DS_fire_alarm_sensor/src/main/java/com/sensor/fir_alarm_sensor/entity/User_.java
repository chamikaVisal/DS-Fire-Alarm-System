package com.sensor.fir_alarm_sensor.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User_ {

    @Id
    @GeneratedValue
    private int id;
    private String userName;
    private String password;
}
