package com.hydrosmart.soil.domain.services.queryservices;

import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.queries.GetHumidityByIdQuery;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface HumidityQueryService {
    Optional<Humidity> handle(GetHumidityByIdQuery query);
}
