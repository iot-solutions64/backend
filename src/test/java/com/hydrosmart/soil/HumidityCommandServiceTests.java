package com.hydrosmart.soil;

import com.hydrosmart.soil.application.internal.commandservices.HumidityCommandServiceImpl;
import com.hydrosmart.soil.domain.model.commands.CreateHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.PatchHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.UpdateHumidityCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.entities.HumidityStatus;
import com.hydrosmart.soil.domain.model.valueobjects.HumidityStatusList;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.HumidityStatusRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class HumidityCommandServiceTests {
    private HumidityRepository humidityRepository;
    private HumidityStatusRepository humidityStatusRepository;
    private HumidityCommandServiceImpl humidityCommandService;

    @BeforeEach
    public void setUp() {
        humidityRepository = mock(HumidityRepository.class);
        humidityStatusRepository = mock(HumidityStatusRepository.class);
        humidityCommandService = new HumidityCommandServiceImpl(humidityRepository, humidityStatusRepository);
    }

    @Test
    public void testCreateHumiditySuccess() {
        CreateHumidityCommand command = new CreateHumidityCommand(60.0f, 50.0f, 70.0f);
        HumidityStatus status = new HumidityStatus(HumidityStatusList.FAVORABLE);

        when(humidityStatusRepository.findByName(HumidityStatusList.FAVORABLE))
                .thenReturn(Optional.of(status));

        Humidity expectedHumidity = new Humidity(command, status);
        when(humidityRepository.save(any(Humidity.class))).thenReturn(expectedHumidity);

        var result = humidityCommandService.handle(command);

        assertTrue(result.isPresent());
        assertEquals(expectedHumidity.getHumidity(), result.get().getHumidity());
        verify(humidityRepository).save(any(Humidity.class));
    }

    @Test
    public void testCreateHumidityInvalidThresholds() {
        CreateHumidityCommand command = new CreateHumidityCommand(60.0f, 80.0f, 70.0f);

        var exception = assertThrows(RuntimeException.class, () -> {
            humidityCommandService.handle(command);
        });

        assertEquals("The min threshold cannot be greater than the max threshold", exception.getMessage());
        verify(humidityRepository, never()).save(any());
    }

    @Test
    public void testUpdateHumiditySuccess() {
        UpdateHumidityCommand command = new UpdateHumidityCommand(1L, 65.0f, 50.0f, 70.0f);
        Humidity existingHumidity = new Humidity();
        existingHumidity.setId(1L);

        HumidityStatus status = new HumidityStatus(HumidityStatusList.FAVORABLE);

        when(humidityRepository.findById(1L)).thenReturn(Optional.of(existingHumidity));
        when(humidityStatusRepository.findByName(any(HumidityStatusList.class)))
                .thenReturn(Optional.of(status));
        when(humidityRepository.save(any())).thenReturn(existingHumidity);

        var result = humidityCommandService.handle(command);

        assertTrue(result.isPresent());
        verify(humidityRepository).save(existingHumidity);
    }

    @Test
    public void testUpdateHumidityHumidityNotFound() {
        UpdateHumidityCommand command = new UpdateHumidityCommand(1L, 60.0f, 50.0f, 70.0f);

        when(humidityRepository.findById(1L)).thenReturn(Optional.empty());

        var exception = assertThrows(RuntimeException.class, () -> {
            humidityCommandService.handle(command);
        });

        assertEquals("Humidity not found", exception.getMessage());
    }

    @Test
    public void testPatchHumiditySuccess() {
        PatchHumidityCommand command = new PatchHumidityCommand(1L, 66.0f);
        Humidity existingHumidity = new Humidity();
        existingHumidity.setId(1L);
        existingHumidity.setHumidityMinThreshold(50.0f);
        existingHumidity.setHumidityMaxThreshold(70.0f);

        HumidityStatus status = new HumidityStatus(HumidityStatusList.FAVORABLE);

        when(humidityRepository.findById(1L)).thenReturn(Optional.of(existingHumidity));
        when(humidityStatusRepository.findByName(any(HumidityStatusList.class)))
                .thenReturn(Optional.of(status));

        humidityCommandService.handle(command);

        verify(humidityRepository).save(existingHumidity);
        assertEquals(66.0f, existingHumidity.getHumidity());
    }

    @Test
    public void testFindStatus_StatusNotFound_Throws() {
        when(humidityStatusRepository.findByName(HumidityStatusList.FLOODED))
                .thenReturn(Optional.empty());

        var command = new CreateHumidityCommand(100.0f, 50.0f, 70.0f);

        assertThrows(RuntimeException.class, () -> {
            humidityCommandService.handle(command);
        });
    }
}
