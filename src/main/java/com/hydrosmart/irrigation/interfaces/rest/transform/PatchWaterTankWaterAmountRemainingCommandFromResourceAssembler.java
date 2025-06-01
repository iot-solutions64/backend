package com.hydrosmart.irrigation.interfaces.rest.transform;

import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankWaterAmountRemainingCommand;
import com.hydrosmart.irrigation.interfaces.rest.resources.PatchWaterTankWaterAmountRemainingResource;

public class PatchWaterTankWaterAmountRemainingCommandFromResourceAssembler {
    public static PatchWaterTankWaterAmountRemainingCommand toCommandFromResource(PatchWaterTankWaterAmountRemainingResource resource){
        return new PatchWaterTankWaterAmountRemainingCommand(
                resource.id(),
                resource.waterAmount()
        );
    }
}
