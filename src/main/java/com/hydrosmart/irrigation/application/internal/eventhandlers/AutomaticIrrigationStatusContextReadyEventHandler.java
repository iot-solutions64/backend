package com.hydrosmart.irrigation.application.internal.eventhandlers;

import java.sql.Timestamp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.commands.SeedAutomaticIrrigationStatusCommand;
import com.hydrosmart.irrigation.domain.services.commandservices.AutomaticIrrigationStatusCommandService;

@Service
public class AutomaticIrrigationStatusContextReadyEventHandler {
    private final AutomaticIrrigationStatusCommandService automaticIrrigationStatusCommandService;
    private static final Logger LOGGER = LoggerFactory
            .getLogger(AutomaticIrrigationStatusContextReadyEventHandler.class);

    public AutomaticIrrigationStatusContextReadyEventHandler(
            AutomaticIrrigationStatusCommandService automaticIrrigationStatusCommandService) {
        this.automaticIrrigationStatusCommandService = automaticIrrigationStatusCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify id the automatic irrigation status seeding is needed for {} at {}",
                applicationName, currentTimestamp());
        var seedAutomaticIrrigationStatusCommand = new SeedAutomaticIrrigationStatusCommand();
        automaticIrrigationStatusCommandService.handle(seedAutomaticIrrigationStatusCommand);
        LOGGER.info("Automatic Irrigation Status seeding verification finished for {} at {}", applicationName,
                currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }

}
