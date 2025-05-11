package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record PatchTemperatureCommand(
        @NotBlank float temperature,
        @NotBlank String temperatureSuggestedActions
) {
}
