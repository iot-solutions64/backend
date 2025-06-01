package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Water Tank Resource</h3>
 * <p>This returns the water tank attributes</p>
 * @param id The id of the water tank
 * @param name The cropName of the water tank
 * @param waterAmountRemaining The amount of water remaining in the tank
 * @param maxWaterCapacity The max amount of water the tank is capable of holding
 * @param status The status of the water tank
 */
public record WaterTankResource(
        Long id,
        String name,
        Float waterAmountRemaining,
        Float maxWaterCapacity,
        String status
) {
}
