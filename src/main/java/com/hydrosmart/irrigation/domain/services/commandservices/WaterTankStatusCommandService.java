package com.hydrosmart.irrigation.domain.services.commandservices;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankStatusCommand;

public interface WaterTankStatusCommandService {
    void handle(SeedWaterTankStatusCommand command);
}
