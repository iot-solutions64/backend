package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Create Humidity Resource</h3>
 * <p>This record allows the creation of the humidity class</p>
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 */
public record CreateHumidityResource(
        Float humidity,
        Float humidityMinThreshold,
        Float humidityMaxThreshold
) {
}
