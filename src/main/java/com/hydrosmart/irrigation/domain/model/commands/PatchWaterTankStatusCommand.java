package com.hydrosmart.irrigation.domain.model.commands;

import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;

public record PatchWaterTankStatusCommand(Long id, WaterTankStatusList status) {
}
