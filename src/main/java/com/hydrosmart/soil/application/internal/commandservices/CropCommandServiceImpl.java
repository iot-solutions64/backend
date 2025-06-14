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
            WaterTankContextFacade waterTankContextFacade) {
        this.cropRepository = cropRepository;
        this.temperatureRepository = temperatureRepository;
        this.humidityRepository = humidityRepository;
        this.userContextFacade = userContextFacade;
        this.irrigationContextFacade = irrigationContextFacade;
        this.waterTankContextFacade = waterTankContextFacade;
    }

    @Override
    public Optional<Crop> handle(CreateCropCommand command) {
        // Validar usuario
        var user = userContextFacade.fetchUserById(command.userId());
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        // Validar temperatura
        var temperatureOpt = temperatureRepository.findById(command.temperatureId());
        if (temperatureOpt.isEmpty()) {
            throw new RuntimeException("Temperature not found");
        }
        var temperature = temperatureOpt.get();

        // Validar humedad
        var humidityOpt = humidityRepository.findById(command.humidityId());
        if (humidityOpt.isEmpty()) {
            throw new RuntimeException("Humidity not found");
        }
        var humidity = humidityOpt.get();

        // Validar riego
        var irrigation = irrigationContextFacade.fetchIrrigationById(command.irrigationId());
        if (irrigation == null) {
            throw new RuntimeException("Irrigation not found");
        }

        // Validar tanque de agua
        var waterTank = waterTankContextFacade.fetchWaterTankById(command.waterTankId());
        if (waterTank == null) {
            throw new RuntimeException("WaterTank not found");
        }

        // Solo si todo es válido, crear y guardar el Crop
        Crop crop = new Crop(command.name(), temperature, humidity, irrigation, waterTank, user);
        cropRepository.save(crop);
        return Optional.of(crop);
    }

    @Override
    public void deleteById(Long cropId) {
        cropRepository.deleteById(cropId);
    }
}
