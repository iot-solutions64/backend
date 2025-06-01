package com.hydrosmart.irrigation.domain.services.commandservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.*;

public interface WaterTankCommandService {
    Optional<WaterTank> handle(CreateWaterTankCommand command);
    Optional<WaterTank> handle(PatchWaterTankNameCommand command);
    Optional<WaterTank> handle(PatchWaterTankWaterAmountRemainingCommand command);
    Optional<WaterTank> handle(PatchWaterTankStatusCommand command);
    void handle(DeleteWaterTankCommand command);
}
