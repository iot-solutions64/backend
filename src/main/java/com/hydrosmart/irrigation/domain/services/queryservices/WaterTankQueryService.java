package com.hydrosmart.irrigation.domain.services.queryservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;

public interface WaterTankQueryService {
    Optional<WaterTank> handle(GetWaterTankByIdQuery query);
}
