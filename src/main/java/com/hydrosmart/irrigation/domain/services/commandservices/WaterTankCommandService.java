package com.hydrosmart.irrigation.domain.services.commandservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.agregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateWaterTankCommand;

public interface WaterTankCommandService {
    Optional<WaterTank> handle(CreateWaterTankCommand command);

    Optional<WaterTank> handle(UpdateWaterTankCommand command);
}
