package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Create Humidity Command</h3>
 * <p>This record allows the creation of an humidity object</p>
 * @param humidity The current humidity of the crop
 * @param humidityMinThreshold The min amount of temperature the crop should get
 * @param humidityMaxThreshold The max amount of temperature the crop should get
 * @param humiditySuggestedActions The actions the user should take to improve the humidity status
 */
public record CreateHumidityCommand(
        @NotBlank float humidity,
        @NotBlank float humidityMinThreshold,
        @NotBlank float humidityMaxThreshold,
        @NotBlank String humiditySuggestedActions
) {
}
