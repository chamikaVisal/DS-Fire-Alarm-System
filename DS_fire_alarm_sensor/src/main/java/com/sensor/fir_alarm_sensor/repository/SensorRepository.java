package com.sensor.fir_alarm_sensor.repository;

import com.sensor.fir_alarm_sensor.entity.SensorApp;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SensorRepository extends JpaRepository<SensorApp, Integer> {
    SensorApp findBySensorId(int id);
}
