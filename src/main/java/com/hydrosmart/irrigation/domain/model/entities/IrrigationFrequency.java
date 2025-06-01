package com.hydrosmart.irrigation.domain.model.entities;

import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationFrequencyCommand;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h3>Irrigation Schedule</h3>
 * <p>Defines the frequency of the automatic irrigation</p>
 */
@Entity
@Getter
@NoArgsConstructor
public class IrrigationFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hourFrequency;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalTime disallowedStartTime;

    private LocalTime disallowedEndTime;

    private int durationInMinutes;

    public IrrigationFrequency(CreateIrrigationFrequencyCommand command){
        this.hourFrequency = command.hourFrequency();
        this.startDate = command.startDate();
        this.startTime = command.startTime();
        this.disallowedStartTime = command.disallowedStartTime();
        this.disallowedEndTime = command.disallowedEndTime();
        this.durationInMinutes = command.durationInMinutes();
    }
}
