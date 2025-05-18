package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotNull;

public record UpdateTemperatureCommand(
        @NotNull Long id,
        @NotNull Float temperature,
        @NotNull Float temperatureMinThreshold,
        @NotNull Float temperatureMaxThreshold
) {}
