package com.hydrosmart.irrigation.application.internal;

import com.hydrosmart.irrigation.application.internal.commandservices.WaterTankCommandServiceImpl;
import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.commands.*;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankRepository;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankStatusRepository;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.interfaces.acl.UserContextFacade;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.acl.CropContextFacade;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class WaterTankCommandServiceImplTest {

    @Test
    void testHandleCreateWaterTankCommand() {
        // Arrange
        WaterTankRepository waterTankRepo = mock(WaterTankRepository.class);
        WaterTankStatusRepository statusRepo = mock(WaterTankStatusRepository.class);
        UserContextFacade userContext = mock(UserContextFacade.class);
        CropContextFacade cropContext = mock(CropContextFacade.class);

        WaterTankCommandServiceImpl service = new WaterTankCommandServiceImpl(
                waterTankRepo, statusRepo, userContext, cropContext
        );

        CreateWaterTankCommand command = new CreateWaterTankCommand("Tank1", 100.0f, 500.0f, 1L);
        WaterTankStatus status = new WaterTankStatus(WaterTankStatusList.DEACTIVATED);
        User user = new User(); // Asegúrate de tener este constructor o usar mock(User.class)

        when(statusRepo.findByName(WaterTankStatusList.DEACTIVATED)).thenReturn(Optional.of(status));
        when(userContext.fetchUserById(command.userId())).thenReturn(user);
        when(waterTankRepo.save(any(WaterTank.class))).thenAnswer(inv -> inv.getArgument(0));

        // Act
        Optional<WaterTank> result = service.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Tank1", result.get().getName());
        assertEquals(status, result.get().getStatus());
    }

    @Test
    void testHandlePatchWaterTankNameCommand() {
        WaterTankRepository waterTankRepo = mock(WaterTankRepository.class);
        WaterTankStatusRepository statusRepo = mock(WaterTankStatusRepository.class);
        UserContextFacade userContext = mock(UserContextFacade.class);
        CropContextFacade cropContext = mock(CropContextFacade.class);

        WaterTankCommandServiceImpl service = new WaterTankCommandServiceImpl(
                waterTankRepo, statusRepo, userContext, cropContext
        );

        PatchWaterTankNameCommand command = new PatchWaterTankNameCommand(1L, "UpdatedName");
        WaterTank tank = mock(WaterTank.class);

        when(waterTankRepo.findById(1L)).thenReturn(Optional.of(tank));
        when(tank.patchName(command)).thenReturn(tank);
        when(waterTankRepo.save(tank)).thenReturn(tank);
        when(tank.getName()).thenReturn("UpdatedName");

        // Act
        Optional<WaterTank> result = service.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("UpdatedName", result.get().getName());
    }

    @Test
    void testHandleDeleteWaterTankCommand() {
        WaterTankRepository waterTankRepo = mock(WaterTankRepository.class);
        CropContextFacade cropContext = mock(CropContextFacade.class);

        WaterTankCommandServiceImpl service = new WaterTankCommandServiceImpl(
                waterTankRepo,
                mock(WaterTankStatusRepository.class),
                mock(UserContextFacade.class),
                cropContext
        );

        DeleteWaterTankCommand command = new DeleteWaterTankCommand(1L);
        List<Crop> crops = List.of(mock(Crop.class), mock(Crop.class));

        when(waterTankRepo.findById(1L)).thenReturn(Optional.of(mock(WaterTank.class)));
        when(cropContext.getCropsByWaterTankId(1L)).thenReturn(crops);

        // Act
        service.handle(command);

        // Assert
        verify(cropContext).saveCrops(crops);
        verify(waterTankRepo).deleteById(1L);
    }
}
