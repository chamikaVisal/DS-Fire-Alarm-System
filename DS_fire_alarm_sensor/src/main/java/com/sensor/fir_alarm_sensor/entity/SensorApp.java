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
public class SensorApp {

    @Id
    @GeneratedValue
    private int Id;
    private int sensorId;
    private int floorNo;
    private int roomNo;
    private int co2Lvl;
    private int smokeLvl;
    private String status;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getCo2Lvl() {
        return co2Lvl;
    }

    public void setCo2Lvl(int co2Lvl) {
        this.co2Lvl = co2Lvl;
    }

    public int getSmokeLvl() {
        return smokeLvl;
    }

    public void setSmokeLvl(int smokeLvl) {
        this.smokeLvl = smokeLvl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getSensorId() {
        return sensorId;
    }

    public void setSensorId(int sensorId) {
        this.sensorId = sensorId;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(int roomNo) {
        this.roomNo = roomNo;
    }
}
