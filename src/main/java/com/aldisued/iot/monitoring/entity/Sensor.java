package com.aldisued.iot.monitoring.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Table(name = "sensors")
@Entity
public class Sensor {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private UUID id;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING) // Task 1
  private SensorType type;


  @OneToMany(mappedBy="sensor")
  private List<Alert> alerts; // Task 2

  @OneToMany(mappedBy="sensor")
  private List<SensorReading> sensorReadings; // Task 2

  public Sensor() {}

  public Sensor(String name, SensorType type) {
    this.name = name;
    this.type = type;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public SensorType getType() {
    return type;
  }

  public void setType(SensorType type) {
    this.type = type;
  }

  public List<Alert> getAlerts() {
    return alerts; // Task 2
  }

  public void setAlerts(List<Alert> alerts) {
    this.alerts = alerts; // Task 2
  }

  public List<SensorReading> getSensorReadings() {
    return sensorReadings; // Task 2
  }

  public void setSensorReadings(List<SensorReading> sensorReadings) {
    this.sensorReadings = sensorReadings; // Task 2
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Sensor sensor = (Sensor) o;
    return Objects.equals(id, sensor.id) && Objects.equals(name, sensor.name)
        && type == sensor.type;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, type);
  }
}
