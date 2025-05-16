package com.hydrosmart.irrigation.application.internal.commandservices;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankStatusCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankStatusCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankStatusRepository;

@Service
public class WaterTankStatusCommandServiceImpl implements WaterTankStatusCommandService {
    private final WaterTankStatusRepository waterTankStatusRepository;

    public WaterTankStatusCommandServiceImpl(WaterTankStatusRepository waterTankStatusRepository) {
        this.waterTankStatusRepository = waterTankStatusRepository;
    }

    @Override
    public void handle(SeedWaterTankStatusCommand command) {
        Arrays.stream(WaterTankStatusList.values()).forEach(status -> {
            if (!waterTankStatusRepository.existsByName(status)) {
                waterTankStatusRepository
                        .save(new WaterTankStatus(WaterTankStatusList.valueOf(status.name())));
            }
        });
    }

}
