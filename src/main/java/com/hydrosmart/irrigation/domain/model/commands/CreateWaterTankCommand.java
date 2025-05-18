package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateWaterTankCommand(
        @NotBlank Float waterTankWaterAmountRemaning,
        @NotBlank Float waterTankMaxWaterCapacity,
        @NotNull Long waterTankWaterAmountStatusId,
        @NotNull Long waterTankStatusId,
        @NotBlank String waterTankQuality) {
}
