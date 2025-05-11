package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Patch Humidity Command</h3>
 * <p>This record allows to change the humidity of the crop</p>
 * @param humidity The humidity of the crop, in °C
 * @param humiditySuggestedActions The actions the user should take to improve the humidity status
 */
public record PatchHumidityCommand(
        @NotBlank float humidity,
        @NotBlank String humiditySuggestedActions
) {}
