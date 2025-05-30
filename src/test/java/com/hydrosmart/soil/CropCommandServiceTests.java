package com.hydrosmart.soil;

import com.hydrosmart.irrigation.interfaces.acl.IrrigationContextFacade;
import com.hydrosmart.irrigation.interfaces.acl.WaterTankContextFacade;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.interfaces.acl.UserContextFacade;
import com.hydrosmart.soil.application.internal.commandservices.CropCommandServiceImpl;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.CropRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CropCommandServiceTests {
    /*
    private CropRepository cropRepository;
    private TemperatureRepository temperatureRepository;
    private HumidityRepository humidityRepository;
    private UserContextFacade userContextFacade;
    private CropCommandServiceImpl cropCommandService;
    private IrrigationContextFacade irrigationContextFacade;
    @Autowired
    private WaterTankContextFacade waterTankContextFacade;

    @BeforeEach
    public void setUp() {
        cropRepository = mock(CropRepository.class);
        temperatureRepository = mock(TemperatureRepository.class);
        humidityRepository = mock(HumidityRepository.class);
        userContextFacade = mock(UserContextFacade.class);
        waterTankContextFacade = mock(WaterTankContextFacade.class);
        cropCommandService = new CropCommandServiceImpl(
                cropRepository,
                temperatureRepository,
                humidityRepository,
                userContextFacade,
                irrigationContextFacade,
                waterTankContextFacade
        );
    }

    @Test
    public void testCreateCropCommandSuccess() {
        // Arrange
        Long userId = 1L, tempId = 10L, humId = 20L, irrId = 30L;
        CreateCropCommand command = new CreateCropCommand("Maíz", userId, tempId, humId, irrId);

        User mockUser = new User("user", "pass");
        Temperature mockTemp = new Temperature();
        Humidity mockHum = new Humidity();

        when(userContextFacade.fetchUserById(userId)).thenReturn(mockUser);
        when(temperatureRepository.findById(tempId)).thenReturn(Optional.of(mockTemp));
        when(humidityRepository.findById(humId)).thenReturn(Optional.of(mockHum));
        when(irrigationContextFacade.fetchIrrigationById(irrId));

        // Act
        Optional<Crop> result = cropCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Maíz", result.get().getName());
        verify(cropRepository, times(1)).save(any(Crop.class));
    }

    @Test
    public void testCreateCropCommandTemperatureNotFound() {
        CreateCropCommand command = new CreateCropCommand("Trigo", 1L, 999L, 20L, 20L);

        when(userContextFacade.fetchUserById(1L)).thenReturn(new User("user", "pass"));
        when(temperatureRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                cropCommandService.handle(command)
        );
        assertEquals("Temperature not found", exception.getMessage());
        verify(cropRepository, never()).save(any());
    }

    @Test
    public void testCreateCropCommandHumidityNotFound() {
        CreateCropCommand command = new CreateCropCommand("Cebada", 1L, 10L, 999L, 20L);

        when(userContextFacade.fetchUserById(1L)).thenReturn(new User("user", "pass"));
        when(temperatureRepository.findById(10L)).thenReturn(Optional.of(new Temperature()));
        when(humidityRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                cropCommandService.handle(command)
        );
        assertEquals("Humidity not found", exception.getMessage());
        verify(cropRepository, never()).save(any());
    }

    @Test
    public void testCreateCropCommandUserNotFound() {
        CreateCropCommand command = new CreateCropCommand("Soja", 99L, 10L, 20L, 20L);

        when(userContextFacade.fetchUserById(99L)).thenReturn(null);

        assertThrows(NullPointerException.class, () ->
                cropCommandService.handle(command)
        );
        verify(cropRepository, never()).save(any());
    }
     */
}
