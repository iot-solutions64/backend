package com.hydrosmart.soil.application.internal.queryservices;

import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.domain.model.queries.GetTemperatureByIdQuery;
import com.hydrosmart.soil.domain.services.queryservices.TemperatureQueryService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureRepository;

import java.util.Optional;

public class TemperatureQueryServiceImpl implements TemperatureQueryService {
    private final TemperatureRepository temperatureRepository;

    public TemperatureQueryServiceImpl(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public Optional<Temperature> handle(GetTemperatureByIdQuery query) {
        return temperatureRepository.findById(query.temperatureId());
    }
}
