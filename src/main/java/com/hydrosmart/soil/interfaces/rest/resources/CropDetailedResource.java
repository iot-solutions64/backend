package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Crop Detailed Resource</h3>
 * <p>This resource includes all of the information of the classes related to the crop</p>
 * @param cropId The id of the crop
 * @param userId The id of the user owner of the crop
 * @param temperature The current temperature of the crop
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 * @param temperatureStatus The current status of the temperature
 * @param temperatureSuggestedActions The actions the user should take to improve the temperature status
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 * @param humidityStatus The current status of the humidity
 * @param humiditySuggestedActions The actions the user should take to improve the humidity status
 */
public record CropDetailedResource(
        Long cropId,
        Long userId,
        Float temperature,
        Float temperatureMinThreshold,
        Float temperatureMaxThreshold,
        String temperatureStatus,
        String temperatureSuggestedActions,
        Float humidity,
        Float humidityMinThreshold,
        Float humidityMaxThreshold,
        String humidityStatus,
        String humiditySuggestedActions
        /*
        Float waterTankWaterAmountRemaining,
        Float waterTankMaxWaterCapacity,
        String waterTankWaterAmountStatus,
        String waterTankStatus,
        String waterTankWaterQuality,
        String automaticIrrigationStatus,
        Float automaticIrrigationMaxWaterUsage,
        Float automaticIrrigationDefaultMaxWaterUsage,
        List<LocalDateTime[]> automaticIrrigationOperationHours,
        String automaticIrrigationSuggestedActions
        */
) {
}
