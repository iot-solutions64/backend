package com.hydrosmart.irrigation.domain.model.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "sensor_data")
public class SensorDataEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double temperature;
    private double humidity;
    private double volume;
    private long timestamp;

    public SensorDataEntity() {
    }

    public SensorDataEntity(double temperature, double humidity, double volume, long timestamp) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.volume = volume;
        this.timestamp = timestamp;
    }

    // Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
