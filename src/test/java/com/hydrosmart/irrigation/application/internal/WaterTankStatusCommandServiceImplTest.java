package com.hydrosmart.irrigation.application.internal;


import com.hydrosmart.irrigation.application.internal.commandservices.WaterTankStatusCommandServiceImpl;
import com.hydrosmart.irrigation.domain.model.commands.SeedWaterTankStatusCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankStatusRepository;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class WaterTankStatusCommandServiceImplTest {

    @Test
    void testHandleSeedsMissingStatuses() {
        // Arrange
        WaterTankStatusRepository mockRepository = mock(WaterTankStatusRepository.class);

        when(mockRepository.existsByName(WaterTankStatusList.DEACTIVATED)).thenReturn(true);
        when(mockRepository.existsByName(WaterTankStatusList.ACTIVATED)).thenReturn(false);
        when(mockRepository.existsByName(WaterTankStatusList.ERROR)).thenReturn(false);

        WaterTankStatusCommandServiceImpl service = new WaterTankStatusCommandServiceImpl(mockRepository);
        SeedWaterTankStatusCommand command = new SeedWaterTankStatusCommand();

        // Act
        service.handle(command);

        // Assert
        verify(mockRepository, times(1)).existsByName(WaterTankStatusList.DEACTIVATED);
        verify(mockRepository, times(1)).existsByName(WaterTankStatusList.ACTIVATED);
        verify(mockRepository, times(1)).existsByName(WaterTankStatusList.ERROR);

        verify(mockRepository, never()).save(new WaterTankStatus(WaterTankStatusList.DEACTIVATED));
        verify(mockRepository, times(1)).save(new WaterTankStatus(WaterTankStatusList.ACTIVATED));
        verify(mockRepository, times(1)).save(new WaterTankStatus(WaterTankStatusList.ERROR));
    }
}
