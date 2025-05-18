package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotNull;

public record CreateIrrigationCommand(
        @NotNull Float maxWaterUsage
) {}