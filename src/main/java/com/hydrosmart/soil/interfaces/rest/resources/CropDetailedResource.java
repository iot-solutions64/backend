package com.hydrosmart.soil.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h3>Crop Detailed Resource</h3>
 * <p>This resource includes all of the information of the classes related to the crop</p>
 * @param cropId The id of the crop
 * @param userId The id of the user owner of the crop
 * @param temperature The current temperature of the crop
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 * @param temperatureStatus The current status of the temperature
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 * @param humidityStatus The current status of the humidity
 * @param waterTankName The cropName of the water tank
 * @param waterAmountRemaining The amount of water remaining in the water tank
 * @param maxWaterCapacity The max water capacity of the water tank
 * @param waterTankStatus The status of the water tank
 * @param irrigationHourFrequency The frequency of the automatic irrigation (in hours)
 * @param irrigationStartDate The starting date of the automatic irrigation
 * @param irrigationStartTime The starting time of the automatic irrigation
 * @param irrigationDisallowedStartTime The hours when the automatic irrigation is prohibited
 * @param irrigationDisallowedEndTime The hours when the automatic irrigation is prohibited
 * @param irrigationDurationInMinutes The irrigationDurationInMinutes of the automatic irrigation (in minutes)
 * @param irrigationStatus The automatic irrigation status
 * @param irrigationMaxWaterUsage The max amount of water the automatic irrigation can use
 */
public record CropDetailedResource(
        //Crop Data
        Long cropId,
        String cropName,
        Long userId,
        //Temperature data
        Float temperature,
        Float temperatureMinThreshold,
        Float temperatureMaxThreshold,
        String temperatureStatus,
        //Humidity data
        Float humidity,
        Float humidityMinThreshold,
        Float humidityMaxThreshold,
        String humidityStatus,
        //Water Tank Data
        String waterTankName,
        Float waterAmountRemaining,
        Float maxWaterCapacity,
        String waterTankStatus,
        //Irrigation Data
        int irrigationHourFrequency,
        LocalDate irrigationStartDate,
        LocalTime irrigationStartTime,
        LocalTime irrigationDisallowedStartTime,
        LocalTime irrigationDisallowedEndTime,
        int irrigationDurationInMinutes,
        String irrigationStatus,
        Float irrigationMaxWaterUsage
) {
}
