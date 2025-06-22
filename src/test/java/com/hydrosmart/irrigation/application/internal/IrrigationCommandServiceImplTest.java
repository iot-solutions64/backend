package com.hydrosmart.irrigation.application.internal;

import com.hydrosmart.irrigation.application.internal.commandservices.IrrigationCommandServiceImpl;
import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.IrrigationStatusList;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationRepository;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationStatusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class IrrigationCommandServiceImplTest {

    @Test
    void testHandleCreateIrrigationCommand() {
        // Arrange
        IrrigationRepository mockIrrigationRepository = mock(IrrigationRepository.class);
        IrrigationStatusRepository mockIrrigationStatusRepository = mock(IrrigationStatusRepository.class);
        IrrigationCommandServiceImpl service = new IrrigationCommandServiceImpl(mockIrrigationRepository, mockIrrigationStatusRepository);

        CreateIrrigationCommand command = new CreateIrrigationCommand(100.0f);
        IrrigationFrequency frequency = new IrrigationFrequency();
        IrrigationStatus status = new IrrigationStatus(IrrigationStatusList.DISABLED);

        when(mockIrrigationStatusRepository.findByName(IrrigationStatusList.DISABLED)).thenReturn(Optional.of(status));
        when(mockIrrigationRepository.save(Mockito.any(Irrigation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Irrigation> result = service.handle(command, frequency);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(command.maxWaterUsage(), result.get().getMaxWaterUsage());
        assertEquals(status, result.get().getIrrigationStatus());
        assertEquals(frequency, result.get().getFrequency());
    }

    @Test
    void testHandleUpdateIrrigationCommand() {
        // Arrange
        IrrigationRepository mockIrrigationRepository = mock(IrrigationRepository.class);
        IrrigationStatusRepository mockIrrigationStatusRepository = mock(IrrigationStatusRepository.class);
        IrrigationCommandServiceImpl service = new IrrigationCommandServiceImpl(mockIrrigationRepository, mockIrrigationStatusRepository);

        UpdateIrrigationCommand command = new UpdateIrrigationCommand(1L, 150.0f);
        IrrigationFrequency frequency = new IrrigationFrequency();
        IrrigationStatus status = new IrrigationStatus(IrrigationStatusList.ENABLED);
        Irrigation existingIrrigation = new Irrigation();

        when(mockIrrigationRepository.findById(command.id())).thenReturn(Optional.of(existingIrrigation));
        when(mockIrrigationRepository.save(Mockito.any(Irrigation.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Optional<Irrigation> result = service.handle(command, status, frequency);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(command.maxWaterUsage(), result.get().getMaxWaterUsage());
        assertEquals(status, result.get().getIrrigationStatus());
        assertEquals(frequency, result.get().getFrequency());
    }
}