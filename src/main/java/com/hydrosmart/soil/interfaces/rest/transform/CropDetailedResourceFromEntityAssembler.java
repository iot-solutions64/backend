package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.rest.resources.CropDetailedResource;

public class CropDetailedResourceFromEntityAssembler {
    public static CropDetailedResource toResourceFromEntity(Crop entity){
        var temperature = entity.getTemperature();
        var humidity = entity.getHumidity();
        var waterTank = entity.getWaterTank();
        var irrigation = entity.getIrrigation();
        var irrigationFrequency = irrigation.getFrequency();
        return new CropDetailedResource(
                //Crop Data
                entity.getId(),
                entity.getName(),
                entity.getUser().getId(),
                //Temperature Data
                temperature.getTemperature(),
                temperature.getTemperatureMinThreshold(),
                temperature.getTemperatureMaxThreshold(),
                temperature.getTemperatureStatus().getTemperatureStatusName(),
                //Humidity Data
                humidity.getHumidity(),
                humidity.getHumidityMinThreshold(),
                humidity.getHumidityMaxThreshold(),
                humidity.getHumidityStatus().getTemperatureStatusName(),
                //Water Tank Data
                waterTank.getName(),
                waterTank.getWaterAmountRemaining(),
                waterTank.getMaxWaterCapacity(),
                waterTank.getStatus().getWaterTankStatusName(),
                //Irrigation Data
                irrigationFrequency.getHourFrequency(),
                irrigationFrequency.getStartDate(),
                irrigationFrequency.getStartTime(),
                irrigationFrequency.getDisallowedStartTime(),
                irrigationFrequency.getDisallowedEndTime(),
                irrigationFrequency.getDurationInMinutes(),
                irrigation.getIrrigationStatus().getAutomaticIrrigationStatusName(),
                irrigation.getMaxWaterUsage()
        );
    }
}
