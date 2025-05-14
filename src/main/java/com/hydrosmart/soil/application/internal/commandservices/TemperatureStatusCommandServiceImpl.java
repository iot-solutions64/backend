package com.hydrosmart.soil.application.internal.commandservices;

import com.hydrosmart.soil.domain.model.commands.SeedTemperatureStatusCommand;
import com.hydrosmart.soil.domain.model.entities.TemperatureStatus;
import com.hydrosmart.soil.domain.model.valueobjects.TemperatureStatusList;
import com.hydrosmart.soil.domain.services.commandservices.TemperatureStatusCommandService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class TemperatureStatusCommandServiceImpl implements TemperatureStatusCommandService {
    private final TemperatureStatusRepository temperatureStatusRepository;

    public TemperatureStatusCommandServiceImpl(TemperatureStatusRepository temperatureStatusRepository) {
        this.temperatureStatusRepository = temperatureStatusRepository;
    }

    /**
     * This method will handle the {@link SeedTemperatureStatusCommand} and will create the temperature status if it does not exist
     * @param command {@link SeedTemperatureStatusCommand}
     * @see SeedTemperatureStatusCommand
     */
    @Override
    public void handle(SeedTemperatureStatusCommand command) {
        Arrays.stream(TemperatureStatusList.values()).forEach(status -> {
           if(!temperatureStatusRepository.existsByName(status)){
               temperatureStatusRepository.save(new TemperatureStatus(TemperatureStatusList.valueOf(status.name())));
           }
        });
    }
}
