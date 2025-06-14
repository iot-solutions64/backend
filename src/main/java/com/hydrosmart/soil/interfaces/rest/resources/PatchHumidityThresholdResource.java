package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Patch Humidity Resource</h3>
 * <p>This record allows the updating of the humidity class</p>
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 */
public record PatchHumidityThresholdResource(
        Float humidityMinThreshold,
        Float humidityMaxThreshold
) {
}
