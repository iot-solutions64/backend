package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;

public record CreateCropCommand(
        @NotBlank Long userId,
        @NotBlank Long temperatureId,
        @NotBlank Long humidityId
) {}
