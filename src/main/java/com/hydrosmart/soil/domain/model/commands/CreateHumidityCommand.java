package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

/**
 * <h3>Create Humidity Command</h3>
 * <p>This record allows the creation of an humidity object</p>
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 */
public record CreateHumidityCommand(
        @NotNull Float humidity,
        @NotNull Float humidityMinThreshold,
        @NotNull Float humidityMaxThreshold
) {
}
