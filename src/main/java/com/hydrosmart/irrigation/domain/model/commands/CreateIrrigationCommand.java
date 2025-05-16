package com.hydrosmart.irrigation.domain.model.commands;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateIrrigationCommand(
        @NotNull Long automaticIrrigationStatusId,
        @NotBlank Float automaticIrrigationMaxWaterUsage,
        @NotBlank Float automaticIrrigationDefaultMaxWaterUsage,
        @NotNull List<LocalDateTime[]> automaticIrrigationOperationHours,
        @NotBlank String automaticIrrigationSuggestedActions) {
}
