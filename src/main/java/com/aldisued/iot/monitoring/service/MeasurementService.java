package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import com.aldisued.iot.monitoring.repository.SensorReadingRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

import org.springframework.stereotype.Service;

@Service
public class MeasurementService {

  private final SensorReadingRepository sensorReadingRepository;

  public MeasurementService(SensorReadingRepository sensorReadingRepository) {
    this.sensorReadingRepository = sensorReadingRepository;
  }

  public List<Double> getMeasurementValuesBySensorType(SensorType sensorType, LocalDateTime from,
      LocalDateTime to) {
    // TODO: Task 8
    return List.of();
  }

  public Optional<Double> getAverageTemperature(LocalDateTime from, LocalDateTime to) {
    // Task 7
    var allTemperature = sensorReadingRepository.findAllBySensorTypeAndTimestampBetween(SensorType.TEMPERATURE, from, to);

    OptionalDouble avg = allTemperature.stream()
            .mapToDouble(SensorReading::getValue)
            .average();

    if (avg.isPresent()) {
      return Optional.of(avg.getAsDouble());
    }

    return Optional.empty();
  }

}
