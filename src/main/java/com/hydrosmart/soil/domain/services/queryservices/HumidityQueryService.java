package com.hydrosmart.soil.domain.services.queryservices;

import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.queries.GetHumidityByIdQuery;

import java.util.Optional;

public interface HumidityQueryService {
    Optional<Humidity> handle(GetHumidityByIdQuery query);
}
