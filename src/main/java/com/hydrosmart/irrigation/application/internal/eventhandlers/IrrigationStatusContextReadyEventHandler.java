package com.hydrosmart.irrigation.application.internal.eventhandlers;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedIrrigationStatusCommand;
import com.hydrosmart.irrigation.domain.services.commandservices.IrrigationStatusCommandService;

@Service
public class IrrigationStatusContextReadyEventHandler {
    private final IrrigationStatusCommandService irrigationStatusCommandService;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(IrrigationStatusContextReadyEventHandler.class);

    public IrrigationStatusContextReadyEventHandler(
            IrrigationStatusCommandService irrigationStatusCommandService) {
        this.irrigationStatusCommandService = irrigationStatusCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify id the automatic irrigation status seeding is needed for {} at {}",
                applicationName, currentTimestamp());
        var seedAutomaticIrrigationStatusCommand = new SeedIrrigationStatusCommand();
        irrigationStatusCommandService.handle(seedAutomaticIrrigationStatusCommand);
        LOGGER.info("Automatic Irrigation Status seeding verification finished for {} at {}", applicationName,
                currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
