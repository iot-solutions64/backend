package com.hydrosmart.irrigation.domain.services.commandservices;

import com.hydrosmart.irrigation.domain.model.commands.SeedIrrigationStatusCommand;

public interface IrrigationStatusCommandService {
    void handle(SeedIrrigationStatusCommand command);
}
