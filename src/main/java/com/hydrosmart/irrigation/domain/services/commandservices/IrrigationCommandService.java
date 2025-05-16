package com.hydrosmart.irrigation.domain.services.commandservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.agregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;

public interface IrrigationCommandService {
    Optional<Irrigation> handle(CreateIrrigationCommand command);

    Optional<Irrigation> handle(UpdateIrrigationCommand command);
}
