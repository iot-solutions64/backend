package com.hydrosmart.irrigation.application.internal.commandservices;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankWaterAmountStatusCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterAmountStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterAmountStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankWaterAmountStatusCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterAmountStatusRepository;

@Service
public class WaterTankWaterAmountStatusCommandServiceImp implements WaterTankWaterAmountStatusCommandService {
    private final WaterAmountStatusRepository waterAmountStatusRepository;

    public WaterTankWaterAmountStatusCommandServiceImp(
            WaterAmountStatusRepository waterAmountStatusRepository) {
        this.waterAmountStatusRepository = waterAmountStatusRepository;
    }

    @Override
    public void handle(SeedWaterTankWaterAmountStatusCommand command) {
        Arrays.stream(WaterAmountStatusList.values()).forEach(status -> {
            if (!waterAmountStatusRepository.existsByName(status)) {
                waterAmountStatusRepository
                        .save(new WaterAmountStatus(WaterAmountStatusList.valueOf(status.name())));
            }
        });
    }

}
