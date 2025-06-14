package com.hydrosmart.irrigation.domain.services.commandservices;

import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;

public interface IrrigationCommandService {
    Optional<Irrigation> handle(CreateIrrigationCommand command, IrrigationFrequency frequency);
    Optional<Irrigation> handle(UpdateIrrigationCommand command, IrrigationStatus status, IrrigationFrequency frequency);
}
