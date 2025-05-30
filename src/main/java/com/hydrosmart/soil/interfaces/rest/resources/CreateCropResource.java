package com.hydrosmart.soil.interfaces.rest.resources;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h3>Create Crop Resource</h3>
 * <p>This resource is used to create a crop</p>
 * @param name The name of the crop
 * @param userId The id of the user owner of the crop
 * @param waterTankId The id of the water tank that the crop will use
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 * @param hourFrequency How often the irrigation occurs
 * @param startDate Which day does the irrigation start
 * @param startTime Which time does the irrigation starts
 * @param disallowedStartTime The starting hour were the irrigation does not occur
 * @param disallowedEndTime The ending hour were the irrigation does not occur
 * @param duration The amount of time the irrigation lasts
 * @param maxWaterUsage The max amount of water the tank can use
 */
public record CreateCropResource(
        String name,
        Long userId,
        Long waterTankId,
        Float temperatureMinThreshold,
        Float temperatureMaxThreshold,
        Float humidityMinThreshold,
        Float humidityMaxThreshold,
        int hourFrequency,
        LocalDate startDate,
        LocalTime startTime,
        LocalTime disallowedStartTime,
        LocalTime disallowedEndTime,
        int duration,
        Float maxWaterUsage
) {}
