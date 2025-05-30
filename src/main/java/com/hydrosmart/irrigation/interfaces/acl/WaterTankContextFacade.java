package com.hydrosmart.irrigation.interfaces.acl;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankCommandService;
import com.hydrosmart.irrigation.domain.services.queryservices.WaterTankQueryService;
import org.springframework.stereotype.Service;

@Service
public class WaterTankContextFacade {
    private final WaterTankQueryService waterTankQueryService;
    private final WaterTankCommandService waterTankCommandService;

    public WaterTankContextFacade(WaterTankQueryService waterTankQueryService, WaterTankCommandService waterTankCommandService) {
        this.waterTankQueryService = waterTankQueryService;
        this.waterTankCommandService = waterTankCommandService;
    }

    public WaterTank fetchWaterTankById(Long waterTankId) {
        var getWaterTankByIdQuery = new GetWaterTankByIdQuery(waterTankId);
        var result = waterTankQueryService.handle(getWaterTankByIdQuery);
        return result.orElse(null);
    }

    public Long createWaterTank(Float maxWaterCapacity){
        //The default amount of water in the water tank will always be equal to the max water capacity
        var createWaterTankCommand = new CreateWaterTankCommand(maxWaterCapacity, maxWaterCapacity);
        var result = waterTankCommandService.handle(createWaterTankCommand);
        if (result.isEmpty()) return 0L;
        return result.get().getId();
    }
}
