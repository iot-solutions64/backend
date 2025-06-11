package com.hydrosmart.irrigation.domain.services.queryservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.queries.GetIrrigationByIdQuery;

public interface IrrigationQueryService {
    Optional<Irrigation> handle(GetIrrigationByIdQuery query);
}