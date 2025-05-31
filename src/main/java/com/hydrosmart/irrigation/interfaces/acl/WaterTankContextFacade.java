package com.hydrosmart.irrigation.interfaces.acl;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.services.queryservices.WaterTankQueryService;
import org.springframework.stereotype.Service;

@Service
public class WaterTankContextFacade {
    private final WaterTankQueryService waterTankQueryService;

    public WaterTankContextFacade(WaterTankQueryService waterTankQueryService) {
        this.waterTankQueryService = waterTankQueryService;
    }

    public WaterTank fetchWaterTankById(Long waterTankId) {
        var getWaterTankByIdQuery = new GetWaterTankByIdQuery(waterTankId);
        var result = waterTankQueryService.handle(getWaterTankByIdQuery);
        return result.orElse(null);
    }
}
