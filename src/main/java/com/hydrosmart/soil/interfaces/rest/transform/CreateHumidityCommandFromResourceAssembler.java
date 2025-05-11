package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.CreateHumidityCommand;
import com.hydrosmart.soil.interfaces.rest.resources.CreateHumidityResource;

public class CreateHumidityCommandFromResourceAssembler {
    public static CreateHumidityCommand toCommandFromResource(CreateHumidityResource resource){
        return new CreateHumidityCommand(
                resource.humidity(),
                resource.humidityMinThreshold(),
                resource.humidityMaxThreshold()
        );
    }
}
