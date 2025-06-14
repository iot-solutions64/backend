package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

public record PatchTemperatureThresholdCommand(
        @NotNull Long id,
        @NotNull Float temperatureMinThreshold,
        @NotNull Float temperatureMaxThreshold
) {}
