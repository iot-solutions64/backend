package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Update Temperature Resource</h3>
 * <p>This record allows the updating of the temperature class</p>
 * @param id The id of the temperature
 * @param temperature The current temperature of the crop
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 */
public record UpdateTemperatureResource(
        Long id,
        float temperature,
        float temperatureMinThreshold,
        float temperatureMaxThreshold
) {
}
