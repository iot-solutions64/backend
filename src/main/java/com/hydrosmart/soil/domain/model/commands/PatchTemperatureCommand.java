package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

/**
 * <h3>Patch Temperature Command</h3>
 * <p>This record allows the patching of the temperature of the crop</p>
 * @param temperature The current temperature of the crop
 * @param temperatureSuggestedActions The actions the user should take to improve the temperature status
 */
public record PatchTemperatureCommand(
        @NotBlank float temperature,
        @NotBlank String temperatureSuggestedActions
) {
}
