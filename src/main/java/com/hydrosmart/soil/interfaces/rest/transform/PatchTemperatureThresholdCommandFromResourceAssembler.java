package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.commands.PatchTemperatureThresholdCommand;
import com.hydrosmart.soil.interfaces.rest.resources.PatchTemperatureThresholdResource;

public class PatchTemperatureThresholdCommandFromResourceAssembler {
    public static PatchTemperatureThresholdCommand toCommandFromResource(PatchTemperatureThresholdResource resource, Long temperatureId) {
        return new PatchTemperatureThresholdCommand(
                temperatureId,
                resource.temperatureMinThreshold(),
                resource.temperatureMaxThreshold()
        );
    }
}
