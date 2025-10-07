package com.aldisued.iot.monitoring.controller;

import com.aldisued.iot.monitoring.dto.SensorDto;
import com.aldisued.iot.monitoring.entity.Sensor;
import com.aldisued.iot.monitoring.service.SensorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sensors")
public class SensorController {

  private final SensorService sensorService;

  public SensorController(SensorService sensorService) {
    this.sensorService = sensorService;
  }

  //Task 4
  @PostMapping
  public ResponseEntity<HttpStatus> saveSensor(@RequestBody SensorDto sensorDto) {
    if (sensorDto.name() == null || sensorDto.name().isBlank()) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    if(sensorService.existsByName(sensorDto.name())){
      return new ResponseEntity<>(HttpStatus.CONFLICT);
    }

    sensorService.saveSensor(sensorDto);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}