package com.hydrosmart.soil.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Temperature Resource</h3>
 * <p>This resource includes all of the info of the temperature object</p>
 * @param temperature The current temperature of the crop
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 * @param temperatureStatus The current status of the temperature
 */
public record TemperatureResource(
        float temperature,
        float temperatureMinThreshold,
        float temperatureMaxThreshold,
        String temperatureStatus
) {}
