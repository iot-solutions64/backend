package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Patch Water Tank Water Amount Resource</h3>
 * <p>Changes the water amount in the water tank</p>
 * @param id The id of the water tank
 * @param waterAmount The water amount inside of the tank
 */
public record PatchWaterTankWaterAmountRemainingResource(
        Long id,
        Float waterAmount
) {
}
