package com.aldisued.iot.monitoring.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MeasurementCalculatorService {

  public List<Double> filterByAverageDeviation(List<Double> values, Double deviation) {
    // Task 9
    if (deviation == null || deviation <= 0 || deviation >= 1) {
      throw new IllegalArgumentException("Deviation must be between 0 and 1");
    }

    double avg = calculateAverage(values);

    List<Double> range = calculateRange(avg, deviation);

    return filterInRange(values, range);

  }

  public List<Double> getMovingAverage(List<Double> data, int windowSize) {
    // Task 10
    int dataSize = data.size();
    if (windowSize <= 0 || windowSize > dataSize) {
      throw new IllegalArgumentException("Invalid window size");
    }

    return getSublist(dataSize, windowSize, data);
  }

  private double calculateAverage(List<Double> doubleListlist) {
    return doubleListlist.stream().mapToDouble(Double::doubleValue).average().orElse(0.0);
  }

  private List<Double> calculateRange(double avg, double deviation) {
    return List.of(avg - avg * deviation, avg + avg * deviation);
  }

  private List<Double> filterInRange(List<Double> values, List<Double> range) {
    return values.stream()
            .filter(v -> v >= range.get(0) && v <= range.get(1))
            .toList();
  }

  private List<Double> getSublist(int dataSize, int windowSize, List<Double> data) {
    List<Double> movingAverages = new ArrayList<>();
    for (int i = 0; i <= dataSize - windowSize; i++) {
      List<Double> sublist = data.subList(i, i + windowSize);
      double avg = calculateAverage(sublist);
      movingAverages.add(avg);
    }
    return movingAverages;
  }

}
