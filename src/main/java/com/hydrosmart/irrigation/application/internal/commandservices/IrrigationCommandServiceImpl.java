package com.hydrosmart.irrigation.application.internal.commandservices;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.IrrigationStatusList;
import com.hydrosmart.irrigation.domain.services.commandservices.IrrigationCommandService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationRepository;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class IrrigationCommandServiceImpl implements IrrigationCommandService {
    private final IrrigationRepository irrigationRepository;
    private final IrrigationStatusRepository irrigationStatusRepository;

    public IrrigationCommandServiceImpl(IrrigationRepository irrigationRepository, IrrigationStatusRepository irrigationStatusRepository) {
        this.irrigationRepository = irrigationRepository;
        this.irrigationStatusRepository = irrigationStatusRepository;
    }

    @Override
    public Optional<Irrigation> handle(CreateIrrigationCommand command, IrrigationFrequency frequency) {
        var status = irrigationStatusRepository.findByName(IrrigationStatusList.DISABLED).orElseThrow(() -> new RuntimeException("Status not found"));
        Irrigation irrigation = new Irrigation(command, status, frequency);
        irrigationRepository.save(irrigation);
        return Optional.of(irrigation);
    }

    @Override
    public Optional<Irrigation> handle(UpdateIrrigationCommand command, IrrigationStatus status, IrrigationFrequency frequency) {
        var foundIrrigation = irrigationRepository.findById(command.id()).orElseThrow(() -> new RuntimeException("Irrigation not found"));
        var updatedIrrigation = irrigationRepository.save(foundIrrigation.updateIrrigation(command,status, frequency));
        return Optional.of(updatedIrrigation);
    }
}
