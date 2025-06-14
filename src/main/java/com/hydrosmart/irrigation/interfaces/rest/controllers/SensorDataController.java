package com.hydrosmart.irrigation.interfaces.rest.controllers;

import com.hydrosmart.irrigation.domain.model.entities.SensorDataEntity;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.SensorDataRepository;
import com.hydrosmart.irrigation.interfaces.rest.resources.SensorDataResource;
import com.hydrosmart.shared.constants.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(AppConstants.API_BASE_PATH + "/iot/data")
public class SensorDataController {
    @Autowired
    private SensorDataRepository sensorDataRepository;

    @PostMapping
    public ResponseEntity<String> receiveSensorData(@RequestBody SensorDataResource sensorData) {
        SensorDataEntity entity = new SensorDataEntity(
                sensorData.getTemperature(),
                sensorData.getHumidity(),
                sensorData.getVolume(),
                sensorData.getTimestamp());
        sensorDataRepository.save(entity);
        return ResponseEntity.ok("Sensor data received and saved");
    }

    @GetMapping
    public ResponseEntity<List<SensorDataResource>> getAllSensorData() {
        List<SensorDataResource> data = sensorDataRepository.findAll().stream()
                .map(e -> {
                    SensorDataResource r = new SensorDataResource();
                    r.setTemperature(e.getTemperature());
                    r.setHumidity(e.getHumidity());
                    r.setVolume(e.getVolume());
                    r.setTimestamp(e.getTimestamp());
                    return r;
                })
                .collect(Collectors.toList());
        return ResponseEntity.ok(data);
    }
}
