package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Create Crop Resource</h3>
 * <p>This resource is used to create a crop</p>
 * @param name The name of the crop
 * @param userId The id of the user owner of the crop
 * @param temperatureId The id of the temperature class related to the crop
 * @param humidityId The id of the humidity class related to the crop
 */
public record CreateCropResource(
        String name,
        Long userId,
        Long temperatureId,
        Long humidityId
) {}
