package com.hydrosmart.irrigation.application.internal.commandservices;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankWaterAmountStatusCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankWaterAmountStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankWaterAmountStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankWaterAmountStatusCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankWaterAmountStatusRepository;

@Service
public class WaterTankWaterAmountStatusCommandServiceImp implements WaterTankWaterAmountStatusCommandService {
    private final WaterTankWaterAmountStatusRepository waterTankWaterAmountStatusRepository;

    public WaterTankWaterAmountStatusCommandServiceImp(
            WaterTankWaterAmountStatusRepository waterTankWaterAmountStatusRepository) {
        this.waterTankWaterAmountStatusRepository = waterTankWaterAmountStatusRepository;
    }

    @Override
    public void handle(SeedWaterTankWaterAmountStatusCommand command) {
        Arrays.stream(WaterTankWaterAmountStatusList.values()).forEach(status -> {
            if (!waterTankWaterAmountStatusRepository.existsByName(status)) {
                waterTankWaterAmountStatusRepository
                        .save(new WaterTankWaterAmountStatus(WaterTankWaterAmountStatusList.valueOf(status.name())));
            }
        });
    }

}
