package com.hydrosmart.soil.application.internal.orchestrators;

import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationFrequencyCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.interfaces.acl.IrrigationContextFacade;
import com.hydrosmart.irrigation.interfaces.acl.WaterTankContextFacade;
import com.hydrosmart.security.interfaces.acl.UserContextFacade;
import com.hydrosmart.soil.domain.exceptions.UserNotFoundException;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.commands.CreateHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.domain.services.commandservices.CropCommandService;
import com.hydrosmart.soil.domain.services.commandservices.HumidityCommandService;
import com.hydrosmart.soil.domain.services.commandservices.TemperatureCommandService;
import com.hydrosmart.soil.interfaces.rest.resources.CreateCropResource;
import com.hydrosmart.soil.interfaces.rest.transform.CreateCropCommandFromResourceAssembler;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class CropOrchestrator {
    private final CropCommandService cropCommandService;
    private final UserContextFacade userContextFacade;
    private final TemperatureCommandService temperatureCommandService;
    private final HumidityCommandService humidityCommandService;
    private final IrrigationContextFacade irrigationContextFacade;
    private final WaterTankContextFacade waterTankContextFacade;

    public CropOrchestrator(
            CropCommandService cropCommandService,
            UserContextFacade userContextFacade,
            TemperatureCommandService temperatureCommandService,
            HumidityCommandService humidityCommandService,
            IrrigationContextFacade irrigationContextFacade,
            WaterTankContextFacade waterTankContextFacade
    ) {
        this.cropCommandService = cropCommandService;
        this.userContextFacade = userContextFacade;
        this.temperatureCommandService = temperatureCommandService;
        this.humidityCommandService = humidityCommandService;
        this.irrigationContextFacade = irrigationContextFacade;
        this.waterTankContextFacade = waterTankContextFacade;
    }

    public Optional<Crop> handle(CreateCropResource resource){
        var user = userContextFacade.fetchUserById(resource.userId());
        if (user == null) throw new UserNotFoundException();

        var waterTank = waterTankContextFacade.fetchWaterTankById(resource.waterTankId());
        if (waterTank == null) throw new RuntimeException("Water Tank not found");

        var createIrrigationFrequencyCommand = new CreateIrrigationFrequencyCommand(
                resource.hourFrequency(),
                resource.startDate(),
                resource.startTime(),
                resource.disallowedStartTime(),
                resource.disallowedEndTime(),
                resource.duration()
        );
        var irrigationId = irrigationContextFacade.createIrrigation(
                new IrrigationFrequency(createIrrigationFrequencyCommand), resource.maxWaterUsage()
        );
        if(irrigationId == 0L) throw new RuntimeException("Could not create irrigation");


        var temp = temperatureCommandService.handle(
                new CreateTemperatureCommand(0f, resource.temperatureMinThreshold(), resource.temperatureMaxThreshold())
        ).orElseThrow(() -> new RuntimeException("Error creating temperature"));

        var humidity = humidityCommandService.handle(
                new CreateHumidityCommand(0f, resource.humidityMinThreshold(), resource.humidityMaxThreshold())
        ).orElseThrow(() -> new RuntimeException("Error creating humidity"));

        var cropCommand = CreateCropCommandFromResourceAssembler.toCommandFromResource(
                resource, temp.getId(), humidity.getId(), irrigationId, waterTank.getId()
        );

        return cropCommandService.handle(cropCommand);
    }
}
