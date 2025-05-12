package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Create Crop Resource</h3>
 * <p>This resource is used to create a crop</p>
 * @param name The name of the crop
 * @param userId The id of the user owner of the crop
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 */
public record CreateCropResource(
        String name,
        Long userId,
        float temperatureMinThreshold,
        float temperatureMaxThreshold,
        float humidityMinThreshold,
        float humidityMaxThreshold
) {}
