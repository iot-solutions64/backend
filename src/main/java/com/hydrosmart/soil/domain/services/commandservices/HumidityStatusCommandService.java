package com.hydrosmart.soil.domain.services.commandservices;

import com.hydrosmart.soil.domain.model.commands.SeedHumidityStatusCommand;

public interface HumidityStatusCommandService {
    void handle(SeedHumidityStatusCommand command);
}
