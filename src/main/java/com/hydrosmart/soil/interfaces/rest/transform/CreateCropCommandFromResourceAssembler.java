package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.interfaces.rest.resources.CreateCropResource;

public class CreateCropCommandFromResourceAssembler {
    public static CreateCropCommand toCommandFromResource(CreateCropResource resource) {
        return new CreateCropCommand(
                resource.userId(),
                resource.temperatureId(),
                resource.humidityId()
        );
    }
}
