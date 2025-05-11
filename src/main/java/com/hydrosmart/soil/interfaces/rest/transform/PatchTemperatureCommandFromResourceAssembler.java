package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.PatchTemperatureCommand;
import com.hydrosmart.soil.interfaces.rest.resources.PatchTemperatureResource;

public class PatchTemperatureCommandFromResourceAssembler {
    public static PatchTemperatureCommand toCommandFromResource(PatchTemperatureResource resource){
        return new PatchTemperatureCommand(
                resource.id(),
                resource.temperature()
        );
    }
}
