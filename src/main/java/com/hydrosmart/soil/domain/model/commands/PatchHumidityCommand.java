package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Patch Humidity Command</h3>
 * <p>This record allows to change the humidity of the crop</p>
 * @param humidity The humidity of the crop, in °C
 */
public record PatchHumidityCommand(
        @NotBlank float humidity
) {}
