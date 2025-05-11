package com.hydrosmart.soil.domain.services.queryservices;

import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.domain.model.queries.GetTemperatureByIdQuery;

import java.util.Optional;

public interface TemperatureQueryService {
    Optional<Temperature> handle(GetTemperatureByIdQuery query);
}
