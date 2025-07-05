package com.hydrosmart.irrigation.domain.model.aggregates;

import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.IrrigationStatusList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IrrigationTest {

    @Test
    void testConstructorWithCreateIrrigationCommand() {
        // Arrange
        CreateIrrigationCommand command = new CreateIrrigationCommand(100.0f);
        IrrigationStatus status = new IrrigationStatus(IrrigationStatusList.DISABLED);
        IrrigationFrequency frequency = new IrrigationFrequency();

        // Act
        Irrigation irrigation = new Irrigation(command, status, frequency);

        // Assert
        assertEquals(command.maxWaterUsage(), irrigation.getMaxWaterUsage());
        assertEquals(status, irrigation.getIrrigationStatus());
        assertEquals(frequency, irrigation.getFrequency());
    }

    @Test
    void testUpdateIrrigation() {
        // Arrange
        Irrigation irrigation = new Irrigation();
        UpdateIrrigationCommand command = new UpdateIrrigationCommand(1L, 150.0f);
        IrrigationStatus newStatus = new IrrigationStatus(IrrigationStatusList.ENABLED);
        IrrigationFrequency newFrequency = new IrrigationFrequency();

        // Act
        irrigation.updateIrrigation(command, newStatus, newFrequency);

        // Assert
        assertEquals(command.maxWaterUsage(), irrigation.getMaxWaterUsage());
        assertEquals(newStatus, irrigation.getIrrigationStatus());
        assertEquals(newFrequency, irrigation.getFrequency());
    }
}