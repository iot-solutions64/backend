package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Patch Temperature Threshold Resource</h3>
 * <p>This record allows the updating of the temperature class</p>
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 */
public record PatchTemperatureThresholdResource(
        Float temperatureMinThreshold,
        Float temperatureMaxThreshold
) {
}
