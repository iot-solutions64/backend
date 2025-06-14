package com.hydrosmart.irrigation.interfaces.rest.resources;

public class SensorDataResource {
    private double temperature;
    private double humidity;
    private double volume;
    private long timestamp;

    public SensorDataResource() {
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

    @Override
    public String toString() {
        return "SensorDataResource{" +
                "temperature=" + temperature +
                ", humidity=" + humidity +
                ", volume=" + volume +
                ", timestamp=" + timestamp +
                '}';
    }
}
