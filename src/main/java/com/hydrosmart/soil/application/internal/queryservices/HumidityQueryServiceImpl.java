package com.hydrosmart.soil.application.internal.queryservices;

import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.queries.GetHumidityByIdQuery;
import com.hydrosmart.soil.domain.services.queryservices.HumidityQueryService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityRepository;

import java.util.Optional;

public class HumidityQueryServiceImpl implements HumidityQueryService {
    private final HumidityRepository humidityRepository;

    public HumidityQueryServiceImpl(HumidityRepository humidityRepository) {
        this.humidityRepository = humidityRepository;
    }

    @Override
    public Optional<Humidity> handle(GetHumidityByIdQuery query) {
        return humidityRepository.findById(query.humidityId());
    }
}