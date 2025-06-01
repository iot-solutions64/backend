package com.hydrosmart.irrigation.interfaces.rest.transform;

import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankNameCommand;
import com.hydrosmart.irrigation.interfaces.rest.resources.PatchWaterTankNameResource;

public class PatchWaterTankNameCommandFromResourceAssembler {
    public static PatchWaterTankNameCommand toCommandFromResource(PatchWaterTankNameResource resource){
        return new PatchWaterTankNameCommand(
                resource.id(),
                resource.name()
        );
    }
}
