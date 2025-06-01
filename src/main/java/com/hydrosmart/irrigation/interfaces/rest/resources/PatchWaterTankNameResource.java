package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Patch Water Tank Name Resource</h3>
 * <p>Changes the name of the water tank</p>
 * @param id The id of the water tank
 * @param name The name of the water tank
 */
public record PatchWaterTankNameResource(
        Long id,
        String name
) {
}
