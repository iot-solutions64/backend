package com.hydrosmart.soil.application.internal.commandservices;

import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureThresholdCommand;
import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.domain.model.entities.TemperatureStatus;
import com.hydrosmart.soil.domain.model.valueobjects.TemperatureStatusList;
import com.hydrosmart.soil.domain.services.commandservices.TemperatureCommandService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TemperatureCommandServiceImpl implements TemperatureCommandService {
    private final TemperatureRepository temperatureRepository;
    private final TemperatureStatusRepository temperatureStatusRepository;
    public TemperatureCommandServiceImpl(TemperatureRepository temperatureRepository, TemperatureStatusRepository temperatureStatusRepository){
        this.temperatureRepository = temperatureRepository;
        this.temperatureStatusRepository = temperatureStatusRepository;
    }
    private void validateTemperature(Float min, Float max){
        if(min > max) throw new RuntimeException("The min threshold cannot be greater than the max threshold");
    }

    private TemperatureStatus findStatus(TemperatureStatusList status){
        return temperatureStatusRepository.findByName(status)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }

    private TemperatureStatus validateTemperatureStatus(Float temperature, Float min, Float max){
        if(temperature >= min && temperature <= max) return findStatus(TemperatureStatusList.FAVORABLE);
        if(temperature > max && temperature <= max + 5) return findStatus(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_OVER);
        if(temperature < min && temperature >= min - 5) return findStatus(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_UNDER);
        if(temperature > max + 5 && temperature <= max + 10) return findStatus(TemperatureStatusList.UNFAVORABLE_OVER);
        if(temperature < min - 5 && temperature >= min - 10) return findStatus(TemperatureStatusList.UNFAVORABLE_UNDER);
        if(temperature > max + 10) return findStatus(TemperatureStatusList.BURNING);
        return findStatus(TemperatureStatusList.FREEZING);
    }

    private Temperature findTemperature(Long id){
        return temperatureRepository
                .findById(id)
                .orElseThrow(() -> new RuntimeException("Temperature not found"));
    }

    @Override
    public Optional<Temperature> handle(CreateTemperatureCommand command) {
        validateTemperature(command.temperatureMinThreshold(), command.temperatureMaxThreshold());
        var status = findStatus(TemperatureStatusList.FAVORABLE);
        var temperature = new Temperature(command, status);
        temperatureRepository.save(temperature);
        return Optional.of(temperature);
    }

    @Override
    public Optional<Temperature> handle(PatchTemperatureThresholdCommand command) {
        var foundTemperature = findTemperature(command.id());
        validateTemperature(command.temperatureMinThreshold(), command.temperatureMaxThreshold());
        var status = validateTemperatureStatus(foundTemperature.getTemperature(), command.temperatureMinThreshold(), command.temperatureMaxThreshold());
        var updatedTemperature = temperatureRepository.save(foundTemperature.patchTemperatureThreshold(command, status));
        return Optional.of(updatedTemperature);
    }

    @Override
    public void handle(PatchTemperatureCommand command) {
        var foundTemperature = findTemperature(command.id());
        validateTemperature(foundTemperature.getTemperatureMinThreshold(), foundTemperature.getTemperatureMaxThreshold());
        var status = validateTemperatureStatus(command.temperature(), foundTemperature.getTemperatureMinThreshold(), foundTemperature.getTemperatureMaxThreshold());
        temperatureRepository.save(foundTemperature.patchTemperature(command, status));
    }
}
