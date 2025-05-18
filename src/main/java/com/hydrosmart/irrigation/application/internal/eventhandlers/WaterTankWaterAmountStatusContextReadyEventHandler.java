package com.hydrosmart.irrigation.application.internal.eventhandlers;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankWaterAmountStatusCommand;
import com.hydrosmart.irrigation.domain.services.commandservices.WaterTankWaterAmountStatusCommandService;

@Service
public class WaterTankWaterAmountStatusContextReadyEventHandler {
    private final WaterTankWaterAmountStatusCommandService waterTankWaterAmountStatusCommandService;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(WaterTankWaterAmountStatusContextReadyEventHandler.class);

    public WaterTankWaterAmountStatusContextReadyEventHandler(
            WaterTankWaterAmountStatusCommandService waterTankWaterAmountStatusCommandService) {
        this.waterTankWaterAmountStatusCommandService = waterTankWaterAmountStatusCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if the water tank water amount status seeding is needed for {} at {}",
                applicationName, currentTimestamp());
        var seedWaterTankWaterAmountStatusCommand = new SeedWaterTankWaterAmountStatusCommand();
        waterTankWaterAmountStatusCommandService.handle(seedWaterTankWaterAmountStatusCommand);
        LOGGER.info("Water Tank Water Amount Status seeding verification finished for {} at {}", applicationName,
                currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
