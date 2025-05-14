package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.interfaces.rest.resources.TemperatureResource;

public class TemperatureResourceFromEntityAssembler {
    public static TemperatureResource toResourceFromEntity(Temperature entity) {
        return new TemperatureResource(
                entity.getTemperature(),
                entity.getTemperatureMinThreshold(),
                entity.getTemperatureMaxThreshold(),
                entity.getTemperatureStatus().getTemperatureStatusName()
        );
    }
}
