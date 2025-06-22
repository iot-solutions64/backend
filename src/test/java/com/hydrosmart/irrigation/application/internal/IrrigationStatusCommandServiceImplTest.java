package com.hydrosmart.irrigation.application.internal;

import com.hydrosmart.irrigation.application.internal.commandservices.IrrigationStatusCommandServiceImpl;
import com.hydrosmart.irrigation.domain.model.commands.SeedIrrigationStatusCommand;
import com.hydrosmart.irrigation.domain.model.valueobjects.IrrigationStatusList;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationStatusRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.argThat;

class IrrigationStatusCommandServiceImplTest {

    @Test
    void testHandleSeedIrrigationStatusCommand() {
        // Arrange
        IrrigationStatusRepository mockRepository = mock(IrrigationStatusRepository.class);
        IrrigationStatusCommandServiceImpl service = new IrrigationStatusCommandServiceImpl(mockRepository);

        SeedIrrigationStatusCommand command = new SeedIrrigationStatusCommand();

        for (IrrigationStatusList status : IrrigationStatusList.values()) {
            when(mockRepository.existsByName(status)).thenReturn(false);
        }

        // Act
        service.handle(command);

        // Assert
        for (IrrigationStatusList status : IrrigationStatusList.values()) {
            verify(mockRepository).existsByName(status);
            verify(mockRepository).save(argThat(savedStatus ->
                    savedStatus.getName().equals(status)
            ));
        }

        verifyNoMoreInteractions(mockRepository);
    }
}
