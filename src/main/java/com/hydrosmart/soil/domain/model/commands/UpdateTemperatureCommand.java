package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record UpdateTemperatureCommand(
        @NotBlank Long id,
        @NotBlank float temperature,
        @NotBlank float temperatureMinThreshold,
        @NotBlank float temperatureMaxThreshold
) {}
