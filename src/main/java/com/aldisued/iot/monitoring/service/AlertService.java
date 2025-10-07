package com.aldisued.iot.monitoring.service;

import com.aldisued.iot.monitoring.dto.AlertDto;
import com.aldisued.iot.monitoring.entity.Alert;
import com.aldisued.iot.monitoring.repository.AlertRepository;
import com.aldisued.iot.monitoring.repository.SensorRepository;
import java.util.UUID;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class AlertService {

  private final AlertRepository alertRepository;
  private final SensorRepository sensorRepository;
  private final KafkaTemplate<String, AlertDto> kafkaTemplate;

  public AlertService(AlertRepository alertRepository, SensorRepository sensorRepository,
      KafkaTemplate<String, AlertDto> kafkaTemplate) {
    this.alertRepository = alertRepository;
    this.sensorRepository = sensorRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  public Alert saveAlert(AlertDto alertDto) {
    // Task 6
    var sensor = sensorRepository.findById(alertDto.sensorId());
    kafkaTemplate.send("alerts", alertDto);
    return alertRepository.save(new Alert(
            alertDto.message(),
            alertDto.timestamp(),
            sensor.orElse(null))
    );
  }

  public AlertDto findLastAlertBySensorId(UUID sensorId) {
    //Task 5
    var recentAlert = alertRepository.findFirstBySensorIdOrderByTimestampDesc(sensorId);

    return recentAlert.map(alert -> new AlertDto(
                    alert.getSensor().getId(),
                    alert.getMessage(),
                    alert.getTimestamp()
            ))
            .orElse(null);
  }
}
