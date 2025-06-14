package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Patch Water Tank Name Resource</h3>
 * <p>Changes the cropName of the water tank</p>
 * @param id The id of the water tank
 * @param name The cropName of the water tank
 */
public record PatchWaterTankNameResource(
        Long id,
        String name
) {
}
