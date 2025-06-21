package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.rest.resources.CropThresholdsResource;

public class CropThresholdsResourceFromEntityAssembler {
    public static CropThresholdsResource toResourceFromEntity(Crop entity){
        return new CropThresholdsResource(
                entity.getId(),
                entity.getName(),
                entity.getUser().getId(),
                entity.getTemperature().getTemperatureMinThreshold(),
                entity.getTemperature().getTemperatureMaxThreshold(),
                entity.getHumidity().getHumidityMinThreshold(),
                entity.getHumidity().getHumidityMaxThreshold()
        );
    }
}
