package com.hydrosmart.soil.application.internal.commandservices;

import com.hydrosmart.security.interfaces.acl.UserContextFacade;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.domain.services.commandservices.CropCommandService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.CropRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CropCommandServiceImpl implements CropCommandService {
    private final CropRepository cropRepository;
    private final TemperatureRepository temperatureRepository;
    private final HumidityRepository humidityRepository;
    private final UserContextFacade userContextFacade;

    public CropCommandServiceImpl(
            CropRepository cropRepository,
            TemperatureRepository temperatureRepository,
            HumidityRepository humidityRepository,
            UserContextFacade userContextFacade) {
        this.cropRepository = cropRepository;
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
        this.userContextFacade = userContextFacade;
    }

    @Override
    public Optional<Crop> handle(CreateCropCommand command) {
        var user = userContextFacade.fetchUserById(command.userId());
        var temperature = temperatureRepository.findById(command.temperatureId()).orElseThrow(() -> new RuntimeException("Temperature not found"));
        var humidity = humidityRepository.findById(command.humidityId()).orElseThrow(() -> new RuntimeException("Humidity not found"));
        try {
            Crop crop = new Crop(temperature, humidity, user);
            cropRepository.save(crop);
            return Optional.of(crop);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating a new crop: " + e.getMessage());
        }
    }
}
