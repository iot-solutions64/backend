package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.rest.resources.CropLightResource;

public class CropLightResourceFromEntityAssembler {
    public static CropLightResource toResourceFromEntity(Crop entity){
        var irrigation = entity.getIrrigation();
        var waterAmountRemaining = entity.getWaterTank() != null ? entity.getWaterTank().getWaterAmountRemaining() : null;
        return new CropLightResource(
                entity.getId(),
                entity.getName(),
                entity.getUser().getId(),
                entity.getTemperature().getTemperature(),
                entity.getHumidity().getHumidity(),
                waterAmountRemaining,
                irrigation.getFrequency().getStartTime(),
                irrigation.getFrequency().getDurationInMinutes(),
                irrigation.getIrrigationStatus().getAutomaticIrrigationStatusName()
        );
    }
}
