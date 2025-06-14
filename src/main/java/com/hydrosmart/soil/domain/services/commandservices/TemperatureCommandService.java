package com.hydrosmart.soil.domain.services.commandservices;

import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureThresholdCommand;
import com.hydrosmart.soil.domain.model.entities.Temperature;

import java.util.Optional;

public interface TemperatureCommandService {
    Optional<Temperature> handle(CreateTemperatureCommand command);
    Optional<Temperature> handle(PatchTemperatureThresholdCommand command);
    void handle(PatchTemperatureCommand command);
}
