package com.hydrosmart.soil.application.internal.eventhandlers;

import com.hydrosmart.soil.domain.model.commands.SeedHumidityStatusCommand;
import com.hydrosmart.soil.domain.services.commandservices.HumidityStatusCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class HumidityStatusContextReadyEventHandler {
    private final HumidityStatusCommandService humidityStatusCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(HumidityStatusContextReadyEventHandler.class);
    public HumidityStatusContextReadyEventHandler(HumidityStatusCommandService humidityStatusCommandService) {
        this.humidityStatusCommandService = humidityStatusCommandService;
    }

    /**
     * Handle the ApplicationReadyEvent
     * This method is used to seed the humidity status
     * @param event the ApplicationReadyEvent the event to handle
     */
    @EventListener
    public void on(ApplicationReadyEvent event){
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify id the humidity status seeding is needed for {} at {}", applicationName, currentTimestamp());
        var seedHumidityStatusCommand = new SeedHumidityStatusCommand();
        humidityStatusCommandService.handle(seedHumidityStatusCommand);
        LOGGER.info("Humidity Status seeding verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
