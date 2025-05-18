package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotNull;

public record UpdateWaterTankCommand(
        @NotNull Long id,
        @NotNull Float waterAmountRemaining,
        @NotNull Float maxWaterCapacity
) {

}
