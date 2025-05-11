package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.UpdateTemperatureCommand;
import com.hydrosmart.soil.interfaces.rest.resources.UpdateTemperatureResource;

public class UpdateTemperatureCommandFromResourceAssembler {
    public static UpdateTemperatureCommand toCommandFromResource(UpdateTemperatureResource resource) {
        return new UpdateTemperatureCommand(
                resource.id(),
                resource.temperature(),
                resource.temperatureMinThreshold(),
                resource.temperatureMaxThreshold()
        );
    }
}
