package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.interfaces.rest.resources.HumidityResource;

public class HumidityResourceFromEntityAssembler {
    public static HumidityResource toResourceFromEntity(Humidity entity) {
        return new HumidityResource(
                entity.getHumidity(),
                entity.getHumidityMinThreshold(),
                entity.getHumidityMinThreshold(),
                entity.getHumidityStatus().getTemperatureStatusName()
        );
    }
}
