package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Crop Reference Resource</h3>
 * <p>This resource only includes the references (ids) of the classes related to the crop</p>
 * @param cropId The id of the crop
 * @param name The name of the crop
 * @param userId The id of the user owner of the crop
 * @param temperatureId The id of the temperature class related to the crop
 * @param humidityId The id of the humidity class related to the crop
 */
public record CropReferenceResource(
        Long cropId,
        String name,
        Long userId,
        Long temperatureId,
        Long humidityId
) {
}
