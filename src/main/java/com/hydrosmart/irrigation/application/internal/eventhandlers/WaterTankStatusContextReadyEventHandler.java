package com.hydrosmart.irrigation.application.internal.eventhandlers;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankStatusCommand;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankStatusCommandService;

@Service
public class WaterTankStatusContextReadyEventHandler {
    private final WaterTankStatusCommandService waterTankStatusCommandService;
    private static final Logger LOGGER = org.slf4j.LoggerFactory
            .getLogger(WaterTankStatusContextReadyEventHandler.class);

    public WaterTankStatusContextReadyEventHandler(WaterTankStatusCommandService waterTankStatusCommandService) {
        this.waterTankStatusCommandService = waterTankStatusCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if the water tank status seeding is needed for {} at {}", applicationName,
                currentTimestamp());
        var seedWaterTankStatusCommand = new SeedWaterTankStatusCommand();
        waterTankStatusCommandService.handle(seedWaterTankStatusCommand);
        LOGGER.info("Water Tank Status seeding verification finished for {} at {}", applicationName,
                currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new java.sql.Timestamp(System.currentTimeMillis());
    }
}
