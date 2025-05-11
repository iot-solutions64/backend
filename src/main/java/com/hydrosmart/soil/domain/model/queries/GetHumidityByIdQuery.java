package com.hydrosmart.soil.domain.model.queries;

public record GetHumidityByIdQuery(Long humidityId) {
    public GetHumidityByIdQuery {
        if (humidityId == null){
            throw new IllegalArgumentException("humidityId cannot be null");
        }
        if (humidityId < 0){
            throw new IllegalArgumentException("humidityId cannot be negative");
        }
    }
}
