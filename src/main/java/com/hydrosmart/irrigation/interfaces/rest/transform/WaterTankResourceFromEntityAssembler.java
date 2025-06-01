package com.hydrosmart.irrigation.interfaces.rest.transform;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.interfaces.rest.resources.WaterTankResource;

public class WaterTankResourceFromEntityAssembler {
    public static WaterTankResource toResourceFromEntity(WaterTank entity) {
        return new WaterTankResource(
                entity.getId(),
                entity.getName(),
                entity.getWaterAmountRemaining(),
                entity.getMaxWaterCapacity(),
                entity.getStatus().getWaterTankStatusName()
        );
    }
}
