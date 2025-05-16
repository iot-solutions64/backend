package com.hydrosmart.irrigation.domain.model.commands;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;

public record UpdateIrrigationCommand(
                @NotNull Long id,
                @NotNull Float automaticIrrigationMaxWaterUsage,
                @NotNull Float automaticIrrigationDefaultMaxWaterUsage,
                @NotNull List<LocalDateTime[]> automaticIrrigationOperationHours,
                @NotNull String automaticIrrigationSuggestedActions) {
}
