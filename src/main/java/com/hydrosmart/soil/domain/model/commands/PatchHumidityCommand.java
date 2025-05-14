package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

/**
 * <h3>Patch Humidity Command</h3>
 * <p>This record allows to change the humidity of the crop</p>
 * @param id The id of the humidity
 * @param humidity The humidity of the crop, in °C
 */
public record PatchHumidityCommand(
        @NotNull Long id,
        @NotNull Float humidity
) {}
