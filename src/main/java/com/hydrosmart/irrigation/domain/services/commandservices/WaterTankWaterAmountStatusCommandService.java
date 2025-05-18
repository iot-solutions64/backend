package com.hydrosmart.irrigation.domain.services.commandservices;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankWaterAmountStatusCommand;

public interface WaterTankWaterAmountStatusCommandService {
    void handle(SeedWaterTankWaterAmountStatusCommand command);
}
