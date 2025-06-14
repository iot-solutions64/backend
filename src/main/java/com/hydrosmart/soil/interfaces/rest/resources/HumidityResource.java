package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Humidity Resource</h3>
 * <p>This resource includes all of the info of the humidity class</p>
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 * @param humidityStatus The current status of the humidity
 */
public record HumidityResource(
        Float humidity,
        Float humidityMinThreshold,
        Float humidityMaxThreshold,
        String humidityStatus
) {
}
