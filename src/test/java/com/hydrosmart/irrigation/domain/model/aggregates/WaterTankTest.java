package com.hydrosmart.irrigation.domain.model.aggregates;

import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankNameCommand;
import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankWaterAmountRemainingCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WaterTankTest {

    @Test
    void testConstructorWithCreateWaterTankCommand() {
        // Arrange
        CreateWaterTankCommand command = new CreateWaterTankCommand("Tank1", 100.0f, 500.0f, 1L);
        WaterTankStatus status = new WaterTankStatus(WaterTankStatusList.DEACTIVATED);
        User user = new User();

        // Act
        WaterTank waterTank = new WaterTank(command, status, user);

        // Assert
        assertEquals(command.name(), waterTank.getName());
        assertEquals(command.waterAmountRemaining(), waterTank.getWaterAmountRemaining());
        assertEquals(command.maxWaterCapacity(), waterTank.getMaxWaterCapacity());
        assertEquals(status, waterTank.getStatus());
        assertEquals(user, waterTank.getUser());
    }

    @Test
    void testPatchName() {
        // Arrange
        WaterTank waterTank = new WaterTank();
        PatchWaterTankNameCommand command = new PatchWaterTankNameCommand(1L, "UpdatedName");

        // Act
        waterTank.patchName(command);

        // Assert
        assertEquals("UpdatedName", waterTank.getName());
    }

    @Test
    void testPatchWaterAmount() {
        // Arrange
        WaterTank waterTank = new WaterTank();
        PatchWaterTankWaterAmountRemainingCommand command = new PatchWaterTankWaterAmountRemainingCommand(1L, 200.0f);

        // Act
        waterTank.patchWaterAmount(command);

        // Assert
        assertEquals(200.0f, waterTank.getWaterAmountRemaining());
    }

    @Test
    void testPatchStatus() {
        // Arrange
        WaterTank waterTank = new WaterTank();
        WaterTankStatus newStatus = new WaterTankStatus(WaterTankStatusList.ACTIVATED);

        // Act
        waterTank.patchStatus(newStatus);

        // Assert
        assertEquals(newStatus, waterTank.getStatus());
    }
}