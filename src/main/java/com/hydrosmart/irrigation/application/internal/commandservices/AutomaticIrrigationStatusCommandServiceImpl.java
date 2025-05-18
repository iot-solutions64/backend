package com.hydrosmart.irrigation.application.internal.commandservices;

import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedAutomaticIrrigationStatusCommand;
import com.hydrosmart.irrigation.domain.model.entities.AutomaticIrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.AutomaticIrrigationStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.AutomaticIrrigationStatusCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.AutomaticIrrigationStatusRepository;

@Service
public class AutomaticIrrigationStatusCommandServiceImpl implements AutomaticIrrigationStatusCommandService {
    private final AutomaticIrrigationStatusRepository automaticIrrigationStatusRepository;

    public AutomaticIrrigationStatusCommandServiceImpl(
            AutomaticIrrigationStatusRepository automaticIrrigationStatusRepository) {
        this.automaticIrrigationStatusRepository = automaticIrrigationStatusRepository;
    }

    @Override
    public void handle(SeedAutomaticIrrigationStatusCommand command) {
        Arrays.stream(AutomaticIrrigationStatusList.values()).forEach(status -> {
            if (!automaticIrrigationStatusRepository.existsByName(status)) {
                automaticIrrigationStatusRepository
                        .save(new AutomaticIrrigationStatus(AutomaticIrrigationStatusList.valueOf(status.name())));
            }
        });
    }

}
