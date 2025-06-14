package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

/**
 * <h3>Patch Humidity Threshold Command</h3>
 * <p>This record allows the updating of the humidity of the crop</p>
 * @param id The id of the humidity
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 */
public record PatchHumidityThresholdCommand(
        @NotNull Long id,
        @NotNull Float humidityMinThreshold,
        @NotNull Float humidityMaxThreshold
) {
}
