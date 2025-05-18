package com.hydrosmart.irrigation.domain.services.queryservices;

import java.util.List;
import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.agregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.queries.GetAllWaterTanksQuery;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;

public interface WaterTankQueryService {
    Optional<WaterTank> handle(GetWaterTankByIdQuery query);

    Optional<List<WaterTank>> handle(GetAllWaterTanksQuery query);
}
