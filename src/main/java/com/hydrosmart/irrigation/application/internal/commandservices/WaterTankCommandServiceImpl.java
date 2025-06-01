package com.hydrosmart.irrigation.application.internal.commandservices;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.*;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankRepository;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankStatusRepository;
import com.hydrosmart.security.interfaces.acl.UserContextFacade;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.acl.CropContextFacade;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WaterTankCommandServiceImpl implements WaterTankCommandService {

    private final WaterTankRepository waterTankRepository;
    private final WaterTankStatusRepository waterTankStatusRepository;
    private final UserContextFacade userContextFacade;
    private final CropContextFacade cropContextFacade;

    public WaterTankCommandServiceImpl(
            WaterTankRepository waterTankRepository,
            WaterTankStatusRepository waterTankStatusRepository,
            UserContextFacade userContextFacade,
            CropContextFacade cropContextFacade
    ) {
        this.waterTankRepository = waterTankRepository;
        this.waterTankStatusRepository = waterTankStatusRepository;
        this.userContextFacade = userContextFacade;
        this.cropContextFacade = cropContextFacade;
    }


    @Override
    public Optional<WaterTank> handle(CreateWaterTankCommand command) {
        var waterTankStatus = waterTankStatusRepository
                .findByName(WaterTankStatusList.DEACTIVATED)
                .orElseThrow(() -> new RuntimeException("Water Tank Status not found"));
        var user = userContextFacade.fetchUserById(command.userId());
        WaterTank waterTank = new WaterTank(command,waterTankStatus, user);
        return Optional.of(waterTankRepository.save(waterTank));
    }

    @Override
    public Optional<WaterTank> handle(PatchWaterTankNameCommand command) {
        WaterTank foundWaterTank = waterTankRepository
                .findById(command.id())
                .orElseThrow(() -> new RuntimeException("Water Tank not found"));
        var patchedWaterTank = waterTankRepository.save(foundWaterTank.patchName(command));
        return Optional.of(patchedWaterTank);
    }

    @Override
    public Optional<WaterTank> handle(PatchWaterTankWaterAmountRemainingCommand command) {
        WaterTank foundWaterTank = waterTankRepository
                .findById(command.id())
                .orElseThrow(() -> new RuntimeException("Water Tank not found"));
        var patchedWaterTank = waterTankRepository.save(foundWaterTank.patchWaterAmount(command));
        return Optional.of(patchedWaterTank);
    }

    @Override
    public Optional<WaterTank> handle(PatchWaterTankStatusCommand command) {
        WaterTank foundWaterTank = waterTankRepository
                .findById(command.id())
                .orElseThrow(() -> new RuntimeException("Water Tank not found"));
        var newStatus = waterTankStatusRepository
                .findByName(command.status())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        var patchedWaterTank = waterTankRepository.save(foundWaterTank.patchStatus(newStatus));
        return Optional.of(patchedWaterTank);
    }

    @Override
    public void handle(DeleteWaterTankCommand command) {
        waterTankRepository
                .findById(command.id())
                .orElseThrow(() -> new RuntimeException("Water Tank not found"));

        List<Crop> cropList = cropContextFacade.getCropsByWaterTankId(command.id());
        for (Crop crop : cropList) {
            crop.setWaterTank(null);
        }
        cropContextFacade.saveCrops(cropList);

        waterTankRepository.deleteById(command.id());
    }
}
