package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record UpdateHumidityCommand(
        @NotBlank float humidity,
        @NotBlank float humidityMinThreshold,
        @NotBlank float humidityMaxThreshold,
        @NotBlank String humiditySuggestedActions
) {
}
