package com.hydrosmart.irrigation.interfaces.rest.transform;


import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankMaxWaterCapacityCommand;
import com.hydrosmart.irrigation.interfaces.rest.resources.PatchWaterTankMaxWaterCapacityResource;

public class PatchWaterTankMaxWaterCapacityCommandFromResourceAssembler {
    public static PatchWaterTankMaxWaterCapacityCommand toCommandFromResource(PatchWaterTankMaxWaterCapacityResource resource, Long cropId){
        return new PatchWaterTankMaxWaterCapacityCommand(
                cropId,
                resource.maxWaterAmount()
        );
    }
}
