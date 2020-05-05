package com.sensor.fir_alarm_sensor.repository;

import com.sensor.fir_alarm_sensor.entity.User_;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User_, Integer> {
}
