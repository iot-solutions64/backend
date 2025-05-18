package com.hydrosmart.irrigation.application.internal.commandservices;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedIrrigationStatusCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.IrrigationStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.IrrigationStatusCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationStatusRepository;

@Service
public class IrrigationStatusCommandServiceImpl implements IrrigationStatusCommandService {
    private final IrrigationStatusRepository irrigationStatusRepository;

    public IrrigationStatusCommandServiceImpl(
            IrrigationStatusRepository irrigationStatusRepository) {
        this.irrigationStatusRepository = irrigationStatusRepository;
    }

    @Override
    public void handle(SeedIrrigationStatusCommand command) {
        Arrays.stream(IrrigationStatusList.values()).forEach(status -> {
            if (!irrigationStatusRepository.existsByName(status)) {
                irrigationStatusRepository
                        .save(new IrrigationStatus(IrrigationStatusList.valueOf(status.name())));
            }
        });
    }

}
