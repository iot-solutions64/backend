package com.hydrosmart.irrigation.domain.services.queryservices;

import java.util.List;
import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTanksByUserId;

public interface WaterTankQueryService {
    Optional<WaterTank> handle(GetWaterTankByIdQuery query);
    List<WaterTank> handle(GetWaterTanksByUserId query);
}
