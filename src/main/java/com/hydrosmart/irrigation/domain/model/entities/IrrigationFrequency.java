package com.hydrosmart.irrigation.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * <h3>Irrigation Schedule</h3>
 * <p>Defines the frequency of the automatic irrigation</p>
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "schedule_type")
@Getter
public class IrrigationFrequency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int hourFrequency;

    private LocalDate startDate;

    private LocalTime startTime;

    private LocalTime allowedStartTime;

    private LocalTime allowedEndTime;

    private int duration;
}
