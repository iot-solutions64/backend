package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Patch Temperature Resource</h3>
 * <p>This record allows the patching of the temperature class</p>
 * @param id The id of the temperature
 * @param temperature The current temperature of the crop
 */
public record PatchTemperatureResource(
        Long id,
        float temperature
) {}
