package com.hydrosmart.soil.application.internal.eventhandlers;

import com.hydrosmart.soil.domain.model.commands.SeedTemperatureStatusCommand;
import com.hydrosmart.soil.domain.services.commandservices.TemperatureStatusCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class TemperatureStatusContextReadyEventHandler {
    private final TemperatureStatusCommandService temperatureStatusCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(TemperatureStatusContextReadyEventHandler.class);
    public TemperatureStatusContextReadyEventHandler(TemperatureStatusCommandService temperatureStatusCommandService) {
        this.temperatureStatusCommandService = temperatureStatusCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the temperature status
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event){
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify id the temperature status seeding is needed for {} at {}", applicationName, currentTimestamp());
        var seedTemperatureStatusCommand = new SeedTemperatureStatusCommand();
        temperatureStatusCommandService.handle(seedTemperatureStatusCommand);
        LOGGER.info("Temperature Status seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
