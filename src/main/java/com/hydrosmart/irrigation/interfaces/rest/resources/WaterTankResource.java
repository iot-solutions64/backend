package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Water Tank Resource</h3>
 * <p>This returns the water tank attributes</p>
 * @param id The id of the water tank
 * @param waterAmountRemaining The amount of water remaining in the tank
 * @param maxWaterCapacity The max amount of water the tank is capable of holding
 * @param waterAmountStatus The status of the water within the tank
 * @param status The status of the water tank
 */
public record WaterTankResource(
        Long id,
        Float waterAmountRemaining,
        Float maxWaterCapacity,
        String waterAmountStatus,
        String status
) {
}
