package com.hydrosmart.soil.interfaces.rest.resources;

public record CropThresholdsResource(
        Long cropId,
        String cropName,
        Long userId,
        Float temperatureMinThreshold,
        Float temperatureMaxThreshold,
        Float humidityMinThreshold,
        Float humidityMaxThreshold
) {}
