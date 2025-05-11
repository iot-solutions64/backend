package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateTemperatureCommand(
        @NotBlank float temperature,
        @NotBlank float temperatureMinThreshold,
        @NotBlank float temperatureMaxThreshold,
        @NotBlank String temperatureSuggestedActions
) {}
