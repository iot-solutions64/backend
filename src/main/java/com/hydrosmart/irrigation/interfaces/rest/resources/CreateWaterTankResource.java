package com.hydrosmart.irrigation.interfaces.rest.resources;

/**
 * <h3>Create Water Tank Resource</h3>
 * <p>This record allows the creation of the water tank</p>
 * @param name The cropName of the water tank
 * @param maxWaterCapacity The max amount of water the water tank is capable of holding
 * @param userId The id of the water tank's user
 */
public record CreateWaterTankResource(
        String name,
        Float maxWaterCapacity,
        Long userId
) {
}
