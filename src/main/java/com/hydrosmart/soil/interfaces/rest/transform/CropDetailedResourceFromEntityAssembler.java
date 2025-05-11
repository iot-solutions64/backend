package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.rest.resources.CropDetailedResource;

public class CropDetailedResourceFromEntityAssembler {
    public static CropDetailedResource toResourceFromEntity(Crop entity){
        var temperature = entity.getTemperature();
        var humidity = entity.getHumidity();
        return new CropDetailedResource(
                entity.getId(),
                entity.getUser().getId(),
                temperature.getTemperature(),
                temperature.getTemperatureMinThreshold(),
                temperature.getTemperatureMaxThreshold(),
                temperature.getTemperatureStatus().toString(),
                temperature.getTemperatureSuggestedActions(),
                humidity.getHumidity(),
                humidity.getHumidityMinThreshold(),
                humidity.getHumidityMaxThreshold(),
                humidity.getHumidityStatus().toString(),
                humidity.getHumiditySuggestedActions()
        );
    }
}
