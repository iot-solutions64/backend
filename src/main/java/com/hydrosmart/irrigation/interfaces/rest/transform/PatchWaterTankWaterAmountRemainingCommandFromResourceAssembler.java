package com.hydrosmart.irrigation.interfaces.rest.transform;

import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankWaterAmountRemainingCommand;
import com.hydrosmart.irrigation.interfaces.rest.resources.PatchWaterTankWaterAmountRemainingResource;

public class PatchWaterTankWaterAmountRemainingCommandFromResourceAssembler {
    public static PatchWaterTankWaterAmountRemainingCommand toCommandFromResource(PatchWaterTankWaterAmountRemainingResource resource, Long cropId){
        return new PatchWaterTankWaterAmountRemainingCommand(
                cropId,
                resource.waterAmount()
        );
    }
}
