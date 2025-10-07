package com.aldisued.iot.monitoring.controller;

import com.aldisued.iot.monitoring.dto.AlertDto;
import com.aldisued.iot.monitoring.service.AlertService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/alerts")
public class AlertController {

  private final AlertService alertService;

  public AlertController(AlertService alertService) {
    this.alertService = alertService;
  }

  //Task 5
  @RequestMapping("/latest")
  public ResponseEntity<Object> getMostRecentAlert(@RequestParam(required = true) UUID sensorId){
    AlertDto alertDto = alertService.findLastAlertBySensorId(sensorId);

    if(alertDto == null){
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    return new ResponseEntity<>(alertDto,HttpStatus.OK);
  }

}
