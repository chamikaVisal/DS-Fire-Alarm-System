package com.sensor.fir_alarm_sensor.controller;

import com.sensor.fir_alarm_sensor.entity.SensorApp;
import com.sensor.fir_alarm_sensor.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.internet.MimeMessage;
import java.util.List;
@CrossOrigin(origins ="*")
@RestController
class SensorController {

    @Autowired
    private SensorService service;

    //add sensor details from desktop app
    @PostMapping("/addSensor")
    public SensorApp addSensorDetails(@RequestBody SensorApp sensor) {
        return service.saveSensor(sensor);
    }

    //edit sensor details from the desktop app
    @PutMapping("/editSensor")
    public SensorApp editSensorDetails(@RequestBody SensorApp desktopapp){
        return service.updateDesktopApp(desktopapp);
    }

    //retrieve data to the web client and desktop app
    @GetMapping("/sensors")
    public List<SensorApp> getSensor() {
        return service.getAllSensorDetails();
    }

    //update sensor details from sensor app
    @PutMapping("/updateSensorApp")
    public SensorApp updateSensorApp(@RequestBody SensorApp sensorApp) {
        return service.updateSensorApp(sensorApp);
    }

    //delete - web client
    @DeleteMapping("/delete/{Id}")
    public String deleteSensorDetails(@PathVariable int Id) {
        return service.deleteSensorDetails(Id);
    }


}
