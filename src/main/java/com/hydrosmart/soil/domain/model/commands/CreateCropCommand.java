package com.hydrosmart.soil.domain.model.commands;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * <h3>Create Crop Command</h3>
 * <p>This record allow the creation of an crop object</p>
 * @param name The name of the crop
 * @param userId The id of the user owner of the crop
 * @param temperatureId The id of the temperature class related to the crop
 * @param humidityId The id of the humidity class related to the crop
 */
public record CreateCropCommand(
        @NotBlank String name,
        @NotNull Long userId,
        @NotNull Long temperatureId,
        @NotNull Long humidityId
) {}
