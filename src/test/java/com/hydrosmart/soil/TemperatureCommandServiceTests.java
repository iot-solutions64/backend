package com.hydrosmart.soil;

import com.hydrosmart.soil.application.internal.commandservices.TemperatureCommandServiceImpl;
import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.UpdateTemperatureCommand;
import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.soil.domain.model.entities.TemperatureStatus;
import com.hydrosmart.soil.domain.model.valueobjects.TemperatureStatusList;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureRepository;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.TemperatureStatusRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TemperatureCommandServiceTests {
        @MockBean
        private TemperatureRepository temperatureRepository;
        @MockBean
        private TemperatureStatusRepository temperatureStatusRepository;
        @Autowired
        private TemperatureCommandServiceImpl temperatureCommandService;

        private final Float MIN = 10.0f;
        private final Float MAX = 30.0f;

        @Test
        void testCreateTemperatureSuccess() {
                // Arrange
                CreateTemperatureCommand command = new CreateTemperatureCommand(25.0f, MIN, MAX);
                TemperatureStatus status = new TemperatureStatus(TemperatureStatusList.FAVORABLE);
                Temperature temperature = new Temperature(command, status);

                Mockito.when(temperatureStatusRepository.findByName(TemperatureStatusList.FAVORABLE))
                                .thenReturn(Optional.of(status));
                Mockito.when(temperatureRepository.save(Mockito.any(Temperature.class)))
                                .thenReturn(temperature);

                // Act
                Optional<Temperature> result = temperatureCommandService.handle(command);

                // Assert
                assertTrue(result.isPresent());
                assertEquals(command.temperature(), result.get().getTemperature());
                assertEquals(TemperatureStatusList.FAVORABLE, result.get().getTemperatureStatus().getName());
        }

        @Test
        void testUpdateTemperatureSuccess() {
                // Arrange
                UpdateTemperatureCommand command = new UpdateTemperatureCommand(1L, 32.0f, MIN, MAX);
                Temperature existing = new Temperature(new CreateTemperatureCommand(25.0f, MIN, MAX),
                                new TemperatureStatus(TemperatureStatusList.FAVORABLE));
                existing.setId(1L);

                TemperatureStatus newStatus = new TemperatureStatus(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_OVER);

                Mockito.when(temperatureRepository.findById(1L))
                                .thenReturn(Optional.of(existing));
                Mockito.when(temperatureStatusRepository.findByName(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_OVER))
                                .thenReturn(Optional.of(newStatus));
                Mockito.when(temperatureRepository.save(Mockito.any(Temperature.class)))
                                .thenAnswer(i -> i.getArguments()[0]);

                // Act
                Optional<Temperature> result = temperatureCommandService.handle(command);

                // Assert
                assertTrue(result.isPresent());
                assertEquals(command.temperature(), result.get().getTemperature());
                assertEquals(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_OVER,
                                result.get().getTemperatureStatus().getName());
        }

        @Test
        void testPatchTemperatureSuccess() {
                // Arrange
                PatchTemperatureCommand command = new PatchTemperatureCommand(1L, 8.0f); // Slightly unfavorable under
                Temperature existing = new Temperature(new CreateTemperatureCommand(15.0f, MIN, MAX),
                                new TemperatureStatus(TemperatureStatusList.FAVORABLE));
                existing.setId(1L);

                TemperatureStatus newStatus = new TemperatureStatus(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_UNDER);

                Mockito.when(temperatureRepository.findById(1L))
                                .thenReturn(Optional.of(existing));
                Mockito.when(temperatureStatusRepository.findByName(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_UNDER))
                                .thenReturn(Optional.of(newStatus));
                Mockito.when(temperatureRepository.save(Mockito.any(Temperature.class)))
                                .thenAnswer(i -> i.getArguments()[0]);

                // Act
                temperatureCommandService.handle(command);

                // Assert
                assertEquals(command.temperature(), existing.getTemperature());
                assertEquals(TemperatureStatusList.SLIGHTLY_UNFAVORABLE_UNDER,
                                existing.getTemperatureStatus().getName());
        }

        @Test
        void testInvalidThreshold() {
                // Arrange
                CreateTemperatureCommand command = new CreateTemperatureCommand(25.0f, 40.0f, 20.0f);

                // Act & Assert
                Exception exception = assertThrows(RuntimeException.class,
                                () -> temperatureCommandService.handle(command));
                assertEquals("The min threshold cannot be greater than the max threshold", exception.getMessage());
        }

        @Test
        void testInvalidStatus() {
                // Arrange
                CreateTemperatureCommand command = new CreateTemperatureCommand(25.0f, MIN, MAX);

                Mockito.when(temperatureStatusRepository.findByName(TemperatureStatusList.FAVORABLE))
                                .thenReturn(Optional.empty());

                // Act & Assert
                assertThrows(RuntimeException.class, () -> temperatureCommandService.handle(command));
        }
}
