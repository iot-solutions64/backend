package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Patch Humidity Resource</h3>
 * <p>This record allows the patching of the humidity class</p>
 * @param id The id of the humidity
 * @param humidity The current humidity of the crop
 */
public record PatchHumidityResource(
        Long id,
        float humidity
) {
}
