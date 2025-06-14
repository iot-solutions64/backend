package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateWaterTankCommand(
        @NotBlank String name,
        @NotBlank Float waterAmountRemaining,
        @NotBlank Float maxWaterCapacity,
        @NotBlank Long userId
) {
}
