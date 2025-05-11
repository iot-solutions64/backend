package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.PatchHumidityCommand;
import com.hydrosmart.soil.interfaces.rest.resources.PatchHumidityResource;

public class PatchHumidityCommandFromResourceAssembler {
    public static PatchHumidityCommand toCommandFromResource(PatchHumidityResource resource) {
        return new PatchHumidityCommand(
                resource.id(),
                resource.humidity()
        );
    }
}
