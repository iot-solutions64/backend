package com.hydrosmart.irrigation.domain.model.commands;

import java.time.LocalDate;
import java.time.LocalTime;

public record CreateIrrigationFrequencyCommand(
        int hourFrequency,
        LocalDate startDate,
        LocalTime startTime,
        LocalTime disallowedStartTime,
        LocalTime disallowedEndTime,
        int duration
) {
}
