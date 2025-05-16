package com.hydrosmart.soil.application.internal.commandservices;

import com.hydrosmart.soil.domain.model.commands.CreateHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.PatchHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.UpdateHumidityCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.entities.HumidityStatus;
import com.hydrosmart.soil.domain.model.valueobjects.HumidityStatusList;
import com.hydrosmart.soil.domain.services.commandservices.HumidityCommandService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityStatusRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class HumidityCommandServiceImpl implements HumidityCommandService {
    private final HumidityRepository humidityRepository;
    private final HumidityStatusRepository humidityStatusRepository;

    public HumidityCommandServiceImpl(HumidityRepository humidityRepository,
            HumidityStatusRepository humidityStatusRepository) {
        this.humidityRepository = humidityRepository;
        this.humidityStatusRepository = humidityStatusRepository;
    }

    private void validateHumidity(Float min, Float max) {
        if (min > max)
            throw new RuntimeException("The min threshold cannot be greater than the max threshold");
    }

    private HumidityStatus findStatus(HumidityStatusList status) {
        return humidityStatusRepository.findByName(status)
                .orElseThrow(() -> new RuntimeException("Status not found"));
    }

    private HumidityStatus validateHumidityStatus(Float humidity, Float min, Float max) {
        if (humidity >= min && humidity <= max)
            return findStatus(HumidityStatusList.FAVORABLE);
        if (humidity > max && humidity < max + 5)
            return findStatus(HumidityStatusList.SLIGHTLY_UNFAVORABLE_OVER);
        if (humidity < min && humidity > min - 5)
            return findStatus(HumidityStatusList.SLIGHTLY_UNFAVORABLE_UNDER);
        if (humidity > max + 5 && humidity < max - 10)
            return findStatus(HumidityStatusList.UNFAVORABLE_OVER);
        if (humidity < min + 5 && humidity > min - 10)
            return findStatus(HumidityStatusList.UNFAVORABLE_UNDER);
        if (humidity > max + 10)
            return findStatus(HumidityStatusList.FLOODED);
        return findStatus(HumidityStatusList.DRY);
    }

    private Humidity findHumidity(Long id) {
        return humidityRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Humidity not found"));
    }

    @Override
    public Optional<Humidity> handle(CreateHumidityCommand command) {
        validateHumidity(command.humidityMinThreshold(), command.humidityMaxThreshold());
        var status = findStatus(HumidityStatusList.FAVORABLE);
        var humidity = new Humidity(command, status);
        humidityRepository.save(humidity);
        return Optional.of(humidity);
    }

    @Override
    public Optional<Humidity> handle(UpdateHumidityCommand command) {
        var foundHumidity = findHumidity(command.id());
        validateHumidity(command.humidityMinThreshold(), command.humidityMaxThreshold());
        var status = validateHumidityStatus(command.humidity(), command.humidityMinThreshold(),
                command.humidityMaxThreshold());
        var updatedHumidity = humidityRepository.save(foundHumidity.updateHumidity(command, status));
        return Optional.of(updatedHumidity);
    }

    @Override
    public void handle(PatchHumidityCommand command) {
        var foundHumidity = findHumidity(command.id());
        validateHumidity(foundHumidity.getHumidityMinThreshold(), foundHumidity.getHumidityMaxThreshold());
        var status = validateHumidityStatus(command.humidity(), foundHumidity.getHumidityMinThreshold(),
                foundHumidity.getHumidityMaxThreshold());
        humidityRepository.save(foundHumidity.patchHumidity(command, status));
    }
}
