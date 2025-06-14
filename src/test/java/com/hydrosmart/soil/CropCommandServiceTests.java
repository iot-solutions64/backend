package com.hydrosmart.soil;

import com.hydrosmart.irrigation.interfaces.acl.IrrigationContextFacade;
import com.hydrosmart.irrigation.interfaces.acl.WaterTankContextFacade;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.security.interfaces.acl.UserContextFacade;
import com.hydrosmart.security.infrastructure.tokens.jwt.BearerTokenService;
import com.hydrosmart.soil.application.internal.commandservices.CropCommandServiceImpl;
import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.CropRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class CropCommandServiceTests {
    @Autowired
    @Qualifier("cropRepository")
    private CropRepository cropRepository;
    @Autowired
    @Qualifier("temperatureRepository")
    private TemperatureRepository temperatureRepository;
    @Autowired
    @Qualifier("humidityRepository")
    private HumidityRepository humidityRepository;
    @Autowired
    @Qualifier("userContextFacade")
    private UserContextFacade userContextFacade;
    @Autowired
    @Qualifier("irrigationContextFacade")
    private IrrigationContextFacade irrigationContextFacade;
    @Autowired
    @Qualifier("waterTankContextFacade")
    private WaterTankContextFacade waterTankContextFacade;
    @Autowired
    @Qualifier("bearerTokenService")
    private BearerTokenService bearerTokenService;
    @Autowired
    private CropCommandServiceImpl cropCommandService;

    @Configuration
    static class MockConfig {
        @Bean
        @Primary
        public CropRepository cropRepository() {
            return org.mockito.Mockito.mock(CropRepository.class);
        }

        @Bean
        @Primary
        public TemperatureRepository temperatureRepository() {
            return org.mockito.Mockito.mock(TemperatureRepository.class);
        }

        @Bean
        @Primary
        public HumidityRepository humidityRepository() {
            return org.mockito.Mockito.mock(HumidityRepository.class);
        }

        @Bean
        @Primary
        public UserContextFacade userContextFacade() {
            return org.mockito.Mockito.mock(UserContextFacade.class);
        }

        @Bean
        @Primary
        public IrrigationContextFacade irrigationContextFacade() {
            return org.mockito.Mockito.mock(IrrigationContextFacade.class);
        }

        @Bean
        @Primary
        public WaterTankContextFacade waterTankContextFacade() {
            return org.mockito.Mockito.mock(WaterTankContextFacade.class);
        }

        @Bean
        @Primary
        public BearerTokenService bearerTokenService() {
            return org.mockito.Mockito.mock(BearerTokenService.class);
        }

        @Bean
        public CropCommandServiceImpl cropCommandServiceImpl(CropRepository cropRepository,
                TemperatureRepository temperatureRepository,
                HumidityRepository humidityRepository,
                UserContextFacade userContextFacade,
                IrrigationContextFacade irrigationContextFacade,
                WaterTankContextFacade waterTankContextFacade) {
            return new CropCommandServiceImpl(cropRepository, temperatureRepository, humidityRepository,
                    userContextFacade, irrigationContextFacade, waterTankContextFacade);
        }
    }

    @Test
    public void testCreateCropCommandSuccess() {
        // Arrange
        Long userId = 1L, tempId = 10L, humId = 20L, irrId = 30L;
        CreateCropCommand command = new CreateCropCommand("Maíz", userId, tempId, humId, irrId, 1L);

        User mockUser = new User("user", "pass");
        Temperature mockTemp = new Temperature();
        Humidity mockHum = new Humidity();

        when(userContextFacade.fetchUserById(userId)).thenReturn(mockUser);
        when(temperatureRepository.findById(tempId)).thenReturn(Optional.of(mockTemp));
        when(humidityRepository.findById(humId)).thenReturn(Optional.of(mockHum));
        when(irrigationContextFacade.fetchIrrigationById(irrId)).thenReturn(TestMocks.mockIrrigation());
        when(waterTankContextFacade.fetchWaterTankById(1L)).thenReturn(TestMocks.mockWaterTank());

        // Act
        Optional<Crop> result = cropCommandService.handle(command);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Maíz", result.get().getName());
        verify(cropRepository, times(1)).save(any(Crop.class));
    }

    @Test
    public void testCreateCropCommandTemperatureNotFound() {
        CreateCropCommand command = new CreateCropCommand("Trigo", 1L, 999L, 20L, 20L, 1L);

        when(userContextFacade.fetchUserById(1L)).thenReturn(new User("user", "pass"));
        when(temperatureRepository.findById(999L)).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cropCommandService.handle(command));
        assertEquals("Temperature not found", exception.getMessage());
        verify(cropRepository, never()).save(any());
    }

    @Test
    public void testCreateCropCommandHumidityNotFound() {
        CreateCropCommand command = new CreateCropCommand("Cebada", 1L, 10L, 999L, 20L, 1L);

        when(userContextFacade.fetchUserById(1L)).thenReturn(new User("user", "pass"));
        when(temperatureRepository.findById(10L)).thenReturn(Optional.of(new Temperature()));
        when(humidityRepository.findById(999L)).thenReturn(Optional.empty());
        when(waterTankContextFacade.fetchWaterTankById(anyLong())).thenReturn(TestMocks.mockWaterTank());
        when(irrigationContextFacade.fetchIrrigationById(anyLong())).thenReturn(TestMocks.mockIrrigation());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> cropCommandService.handle(command));
        assertEquals("Humidity not found", exception.getMessage());
        verify(cropRepository, never()).save(any());
    }

    @Test
    public void testCreateCropCommandUserNotFound() {
        CreateCropCommand command = new CreateCropCommand("Soja", 99L, 10L, 20L, 20L, 1L);

        when(userContextFacade.fetchUserById(99L)).thenReturn(null);
        when(waterTankContextFacade.fetchWaterTankById(anyLong())).thenReturn(TestMocks.mockWaterTank());
        when(irrigationContextFacade.fetchIrrigationById(anyLong())).thenReturn(TestMocks.mockIrrigation());

        assertThrows(RuntimeException.class, () -> cropCommandService.handle(command));
        verify(cropRepository, never()).save(any());
    }
}
