package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotNull;

public record UpdateIrrigationCommand(
        @NotNull Long id,
        @NotNull Float maxWaterUsage
) {
}
