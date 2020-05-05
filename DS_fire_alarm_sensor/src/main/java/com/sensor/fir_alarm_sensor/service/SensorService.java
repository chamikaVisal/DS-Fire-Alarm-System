package com.sensor.fir_alarm_sensor.service;

import com.sensor.fir_alarm_sensor.entity.SensorApp;
import com.sensor.fir_alarm_sensor.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.internet.MimeMessage;
import java.util.List;

@Service
public class SensorService {

    @Autowired
    private SensorRepository srepo;

    @Autowired
    private JavaMailSender mailSender;

    //add sensor id, room no and floor no from the desktop app
    public SensorApp saveSensor(SensorApp sensorApp){
        return srepo.save(sensorApp);
    }

    //read sensor details - webclient and desktop client
    public List<SensorApp> getAllSensorDetails() {
        return srepo.findAll();
    }

    //update co2 lvl, smoke lvl from the sensor app
    public SensorApp updateSensorApp(SensorApp sensorApp) {

        SensorApp exisitingSensor = srepo.findById(sensorApp.getId()).orElse(null);
        exisitingSensor.setSensorId(sensorApp.getSensorId());
        exisitingSensor.setCo2Lvl(sensorApp.getCo2Lvl());
        exisitingSensor.setSmokeLvl(sensorApp.getSmokeLvl());

        // check for the sensor value
        if (sensorApp.getCo2Lvl() > 5 || sensorApp.getSmokeLvl() > 5) {
            // if the values are greater than than 5 send the email
            sendMail(exisitingSensor.getSensorId(), exisitingSensor.getFloorNo(), exisitingSensor.getRoomNo());
        }
        return srepo.save(exisitingSensor);
    }

    //edit room no, floor no from desktop app
    public SensorApp updateDesktopApp(SensorApp desktopApp){
        SensorApp ex = srepo.findBySensorId(desktopApp.getSensorId());
        if (ex != null) {
            ex.setSensorId(desktopApp.getSensorId());
            ex.setRoomNo(desktopApp.getRoomNo());
            ex.setFloorNo(desktopApp.getFloorNo());
        } else {
            return null;
        }
        return srepo.save(ex);
    }

    private void sendMail(int sensorId, int floorNo, int roomNo) {
        //    send an email to the admin
        MimeMessage msg = mailSender.createMimeMessage();

        MimeMessageHelper helper;
        try {
            helper = new MimeMessageHelper(msg, true);
            helper.setFrom("no-reply@gigara.info");
            helper.setTo("yapanishiki@gmail.com");
            helper.setSubject("Warning!!!");
            helper.setText("<h2>Abnormal Sensor event detected.</h2>" +
                    "Sensor ID: " + sensorId +
                    "--------  Room No: </br>" + roomNo +
                    "--------  Floor No: " + floorNo +
                    "<h3>Take necessary actions ASAP!</h3>", true);
            mailSender.send(msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //delete - web client
    public String deleteSensorDetails(int Id) {
        srepo.deleteById(Id);
        return "Sensor removed!";
    }
}
