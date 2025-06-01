package com.hydrosmart.irrigation.domain.services.commandservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankNameCommand;
import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankStatusCommand;
import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankWaterAmountRemainingCommand;

public interface WaterTankCommandService {
    Optional<WaterTank> handle(CreateWaterTankCommand command);
    Optional<WaterTank> handle (PatchWaterTankNameCommand command);
    Optional<WaterTank> handle(PatchWaterTankWaterAmountRemainingCommand command);
    Optional<WaterTank> handle(PatchWaterTankStatusCommand command);
}
