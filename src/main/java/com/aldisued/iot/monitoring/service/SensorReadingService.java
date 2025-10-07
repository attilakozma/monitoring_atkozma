package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.SensorReadingDto;
import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.repository.SensorReadingRepository;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import org.springframework.stereotype.Service;

@Service
public class SensorReadingService {

  private final SensorReadingRepository sensorReadingRepository;
  private final SensorRepository sensorRepository;

  public SensorReadingService(SensorReadingRepository sensorReadingRepository,
      SensorRepository sensorRepository) {
    this.sensorReadingRepository = sensorReadingRepository;
    this.sensorRepository = sensorRepository;
  }

  public SensorReading saveSensorReading(SensorReadingDto sensorReadingDto) {
    // Task 3
    var sensor = sensorRepository.findById(sensorReadingDto.sensorId());
    if(sensor.isEmpty()){
      throw new IllegalArgumentException("Sensor with id " + sensorReadingDto.sensorId() + " not found");
    }

    var sensorReading = new SensorReading(
            sensorReadingDto.value(),
            sensorReadingDto.timestamp(),
            sensor.get()
    );
    return sensorReadingRepository.save(sensorReading);
  }

}
