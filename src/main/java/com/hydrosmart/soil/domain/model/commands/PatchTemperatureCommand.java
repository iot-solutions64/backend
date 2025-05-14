package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

/**
 * <h3>Patch Temperature Command</h3>
 * <p>This record allows the patching of the temperature of the crop</p>
 * @param temperature The current temperature of the crop
 */
public record PatchTemperatureCommand(
        @NotNull Long id,
        @NotNull Float temperature
) {
}
