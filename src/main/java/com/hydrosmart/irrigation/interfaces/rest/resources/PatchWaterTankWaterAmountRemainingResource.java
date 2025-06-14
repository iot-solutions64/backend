package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Patch Water Tank Water Amount Resource</h3>
 * <p>Changes the water amount in the water tank</p>
 * @param waterAmount The water amount inside the tank
 */
public record PatchWaterTankWaterAmountRemainingResource(
        Float waterAmount
) {
}
