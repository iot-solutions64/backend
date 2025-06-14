package com.hydrosmart.soil.domain.services.commandservices;

import com.hydrosmart.soil.domain.model.commands.CreateHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.PatchHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.PatchHumidityThresholdCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;

import java.util.Optional;

public interface HumidityCommandService {
    Optional<Humidity> handle(CreateHumidityCommand command);
    Optional<Humidity> handle(PatchHumidityThresholdCommand command);
    void handle(PatchHumidityCommand command);
}
