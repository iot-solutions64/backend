package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateWaterTankCommand(
        @NotBlank Float waterAmountRemaining,
        @NotBlank Float maxWaterCapacity
) {
}
