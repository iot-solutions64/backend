package com.hydrosmart.irrigation.interfaces.rest.transform;

import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.interfaces.rest.resources.CreateWaterTankResource;

public class CreateWaterTankCommandFromResourceAssembler {
    public static CreateWaterTankCommand toCommandFromResource(CreateWaterTankResource resource){
        return new CreateWaterTankCommand(
                resource.name(),
                resource.maxWaterCapacity(),
                resource.maxWaterCapacity(),
                resource.userId()
        );
    }
}
