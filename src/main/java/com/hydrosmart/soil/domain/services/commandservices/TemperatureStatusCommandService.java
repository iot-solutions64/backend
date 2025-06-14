package com.hydrosmart.soil.domain.services.commandservices;

import com.hydrosmart.soil.domain.model.commands.SeedTemperatureStatusCommand;

public interface TemperatureStatusCommandService {
    void handle(SeedTemperatureStatusCommand command);
}
