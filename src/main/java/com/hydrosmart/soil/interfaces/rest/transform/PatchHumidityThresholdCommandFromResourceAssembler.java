package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.PatchHumidityThresholdCommand;
import com.hydrosmart.soil.interfaces.rest.resources.PatchHumidityThresholdResource;

public class PatchHumidityThresholdCommandFromResourceAssembler {
    public static PatchHumidityThresholdCommand toCommandFromResource(PatchHumidityThresholdResource resource, Long humidityId) {
        return new PatchHumidityThresholdCommand(
                humidityId,
                resource.humidityMinThreshold(),
                resource.humidityMaxThreshold()
        );
    }
}
