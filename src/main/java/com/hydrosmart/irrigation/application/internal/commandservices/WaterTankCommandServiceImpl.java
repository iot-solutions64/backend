package com.hydrosmart.irrigation.application.internal.commandservices;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterAmountStatusList;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankRepository;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankStatusRepository;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterAmountStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WaterTankCommandServiceImpl implements WaterTankCommandService {

    private final WaterTankRepository waterTankRepository;
    private final WaterTankStatusRepository waterTankStatusRepository;
    private final WaterAmountStatusRepository waterAmountStatusRepository;

    public WaterTankCommandServiceImpl(
            WaterTankRepository waterTankRepository,
            WaterTankStatusRepository waterTankStatusRepository,
            WaterAmountStatusRepository waterAmountStatusRepository
    ) {
        this.waterTankRepository = waterTankRepository;
        this.waterTankStatusRepository = waterTankStatusRepository;
        this.waterAmountStatusRepository = waterAmountStatusRepository;
    }


    @Override
    public Optional<WaterTank> handle(CreateWaterTankCommand command) {
        var waterAmountStatus = waterAmountStatusRepository.findByName(WaterAmountStatusList.NORMAL).orElseThrow(() -> new RuntimeException("Water Amount Status not found"));
        var waterTankStatus = waterTankStatusRepository.findByName(WaterTankStatusList.DEACTIVATED).orElseThrow(() -> new RuntimeException("Water Tank Status not found"));
        WaterTank waterTank = new WaterTank(command,waterAmountStatus,waterTankStatus);
        return Optional.of(waterTankRepository.save(waterTank));
    }

    @Override
    public Optional<WaterTank> handle(UpdateWaterTankCommand command) {
        WaterTank foundWaterTank = waterTankRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Water Tank not found"));
        //TODO implement a better way to handle the status
        var waterAmountStatus = waterAmountStatusRepository.findByName(WaterAmountStatusList.NORMAL).orElseThrow(() -> new RuntimeException("Water Amount Status not found"));
        var waterTankStatus = waterTankStatusRepository.findByName(WaterTankStatusList.DEACTIVATED).orElseThrow(() -> new RuntimeException("Water Tank Status not found"));
        var updatedWaterTank = waterTankRepository.save(foundWaterTank.updateWaterTank(command,waterAmountStatus,waterTankStatus));
        return Optional.of(updatedWaterTank);
    }
}
