package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.UpdateHumidityCommand;
import com.hydrosmart.soil.interfaces.rest.resources.UpdateHumidityResource;

public class UpdateHumidityCommandFromResourceAssembler {
    public static UpdateHumidityCommand toCommandFromResource(UpdateHumidityResource resource) {
        return new UpdateHumidityCommand(
                resource.id(),
                resource.humidity(),
                resource.humidityMinThreshold(),
                resource.humidityMaxThreshold()
        );
    }
}
