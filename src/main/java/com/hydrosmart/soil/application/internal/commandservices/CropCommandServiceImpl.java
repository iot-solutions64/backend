package com.hydrosmart.soil.application.internal.commandservices;

import com.hydrosmart.irrigation.interfaces.acl.IrrigationContextFacade;
import com.hydrosmart.irrigation.interfaces.acl.WaterTankContextFacade;
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
    private final IrrigationContextFacade irrigationContextFacade;
    private final WaterTankContextFacade waterTankContextFacade;

    public CropCommandServiceImpl(
            CropRepository cropRepository,
            TemperatureRepository temperatureRepository,
            HumidityRepository humidityRepository,
            UserContextFacade userContextFacade,
            IrrigationContextFacade irrigationContextFacade,
            WaterTankContextFacade waterTankContextFacade
    ) {
        this.cropRepository = cropRepository;
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
        this.userContextFacade = userContextFacade;
        this.irrigationContextFacade = irrigationContextFacade;
        this.waterTankContextFacade = waterTankContextFacade;
    }

    @Override
    public Optional<Crop> handle(CreateCropCommand command) {
        var user = userContextFacade.fetchUserById(command.userId());
        var temperature = temperatureRepository.findById(command.temperatureId()).orElseThrow(() -> new RuntimeException("Temperature not found"));
        var humidity = humidityRepository.findById(command.humidityId()).orElseThrow(() -> new RuntimeException("Humidity not found"));
        var irrigation = irrigationContextFacade.fetchIrrigationById(command.irrigationId());
        if(irrigation == null) throw new RuntimeException("Irrigation not found");
        var waterTank = waterTankContextFacade.fetchWaterTankById(command.waterTankId());
        if(waterTank == null) throw new RuntimeException("WaterTank not found");
        try {
            Crop crop = new Crop(command.name(),temperature, humidity, irrigation, waterTank,user);
            cropRepository.save(crop);
            return Optional.of(crop);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while creating a new crop: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long cropId) {
        cropRepository.deleteById(cropId);
    }
}
