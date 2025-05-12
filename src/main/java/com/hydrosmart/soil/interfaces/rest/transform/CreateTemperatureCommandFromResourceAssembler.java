package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.interfaces.rest.resources.CreateTemperatureResource;

public class CreateTemperatureCommandFromResourceAssembler {
    public static CreateTemperatureCommand toCommandFromResource(CreateTemperatureResource resource) {
        return new CreateTemperatureCommand(
                resource.temperature(),
                resource.temperatureMinThreshold(),
                resource.temperatureMaxThreshold()
        );
    }
}
