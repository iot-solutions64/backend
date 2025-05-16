package com.hydrosmart.irrigation.domain.services.commandservices;

import com.hydrosmart.irrigation.domain.model.commands.SeedAutomaticIrrigationStatusCommand;

public interface AutomaticIrrigationStatusCommandService {
    void handle(SeedAutomaticIrrigationStatusCommand command);
}
