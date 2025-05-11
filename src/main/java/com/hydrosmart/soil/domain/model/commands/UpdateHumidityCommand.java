package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Update Humidity Command</h3>
 * <p>This record allows the updating of the humidity of the crop</p>
 * @param id The id of the humidity
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 */
public record UpdateHumidityCommand(
        @NotBlank Long id,
        @NotBlank float humidity,
        @NotBlank float humidityMinThreshold,
        @NotBlank float humidityMaxThreshold
) {
}
