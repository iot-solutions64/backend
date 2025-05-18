package com.hydrosmart.soil.application.internal.commandservices;

import com.hydrosmart.soil.domain.model.commands.SeedHumidityStatusCommand;
import com.hydrosmart.soil.domain.model.entities.HumidityStatus;
import com.hydrosmart.soil.domain.model.valueobjects.HumidityStatusList;
import com.hydrosmart.soil.domain.services.commandservices.HumidityStatusCommandService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class HumidityStatusCommandServiceImpl implements HumidityStatusCommandService {
    private final HumidityStatusRepository humidityStatusRepository;

    public HumidityStatusCommandServiceImpl(HumidityStatusRepository humidityStatusRepository) {
        this.humidityStatusRepository = humidityStatusRepository;
    }

    /**
     * This method will handle the {@link SeedHumidityStatusCommand} and will create the humidity status if it does not exist
     * @param command {@link SeedHumidityStatusCommand}
     * @see SeedHumidityStatusCommand
     */
    @Override
    public void handle(SeedHumidityStatusCommand command) {
        Arrays.stream(HumidityStatusList.values()).forEach(status -> {
            if(!humidityStatusRepository.existsByName(status)){
                humidityStatusRepository.save(new HumidityStatus(HumidityStatusList.valueOf(status.name())));
            }
        });
    }
}
