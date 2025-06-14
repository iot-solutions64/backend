package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.interfaces.rest.resources.CreateCropResource;

public class CreateCropCommandFromResourceAssembler {
    public static CreateCropCommand toCommandFromResource(
            CreateCropResource resource,
            Long temperatureId,
            Long humidityId,
            Long irrigationId,
            Long waterTankId
    ) {
        return new CreateCropCommand(
                resource.name(),
                resource.userId(),
                temperatureId,
                humidityId,
                irrigationId,
                waterTankId
        );
    }
}
