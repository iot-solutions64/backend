package com.hydrosmart.soil.domain.model.queries;

public record GetTemperatureByIdQuery(Long temperatureId) {
    public GetTemperatureByIdQuery {
        if (temperatureId == null){
            throw new IllegalArgumentException("temperatureId cannot be null");
        }
        if (temperatureId < 0){
            throw new IllegalArgumentException("temperatureId cannot be negative");
        }
    }
}
