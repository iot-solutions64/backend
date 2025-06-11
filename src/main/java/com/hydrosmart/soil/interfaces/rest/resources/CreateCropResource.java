package com.hydrosmart.soil.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h3>Create Crop Resource</h3>
 * <p>This resource is used to create a crop</p>
 * @param name The cropName of the crop
 * @param userId The id of the user owner of the crop
 * @param waterTankId The id of the water tank that the crop will use
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 * @param hourFrequency How often the irrigation occurs
 * @param irrigationStartDate Which day does the irrigation start
 * @param irrigationStartTime Which time does the irrigation starts
 * @param irrigationDisallowedStartTime The starting hour were the irrigation does not occur
 * @param irrigationDisallowedEndTime The ending hour were the irrigation does not occur
 * @param irrigationDurationInMinutes The amount of time the irrigation lasts
 * @param irrigationMaxWaterUsage The max amount of water the tank can use
 */
public record CreateCropResource(
        //Crop Data
        String name,
        Long userId,
        Long waterTankId,
        //Temperature Data
        Float temperatureMinThreshold,
        Float temperatureMaxThreshold,
        //Humidity Data
        Float humidityMinThreshold,
        Float humidityMaxThreshold,
        //Irrigation Data
        int hourFrequency,
        LocalDate irrigationStartDate,
        LocalTime irrigationStartTime,
        LocalTime irrigationDisallowedStartTime,
        LocalTime irrigationDisallowedEndTime,
        int irrigationDurationInMinutes,
        Float irrigationMaxWaterUsage
) {}
