package com.hydrosmart.irrigation.domain.services.queryservices;

import java.util.List;
import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.agregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.queries.GetAllIrrigationsQuery;
import com.hydrosmart.irrigation.domain.model.queries.GetIrrigationByIdQuery;

public interface IrrigationQueryService {
    Optional<Irrigation> handle(GetIrrigationByIdQuery query);

    Optional<List<Irrigation>> handle(GetAllIrrigationsQuery query);
}