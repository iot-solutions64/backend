package com.hydrosmart.irrigation.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateCropCommand(
        @NotBlank Long temperatureId,
        @NotBlank Long humidityId
) {
}
