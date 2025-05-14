package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

/**
 * <h3>Create Temperature Command</h3>
 * <p>This record allows the creation of the temperature object</p>
 * @param temperature The current temperature of the crop
 * @param temperatureMinThreshold The min amount of temperature the crop should get
 * @param temperatureMaxThreshold The max amount of temperature the crop should get
 */
public record CreateTemperatureCommand(
        @NotNull Float temperature,
        @NotNull Float temperatureMinThreshold,
        @NotNull Float temperatureMaxThreshold
) {}
