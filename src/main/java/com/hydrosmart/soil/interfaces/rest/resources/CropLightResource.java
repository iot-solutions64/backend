package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Crop Light Resource</h3>
 * <p>This resource returns the most important data of the classes related to the crop</p>
 * @param cropId The id of the crop
 * @param userId The id of the user owner of the crop
 * @param temperature The current temperature of the crop
 * @param humidity The current humidity of the crop
 * //@param waterAmountRemaining The amount of water remaining in the water tank related to the crop
 * //@param maxWaterCapacity The capacity of the water tank related to the crop
 * //@param automaticIrrigationStatus The status of the irrigation
 */
public record CropLightResource(
        Long cropId,
        Long userId,
        Float temperature,
        Float humidity
        /*
        Float waterAmountRemaining,
        Float maxWaterCapacity,
        String automaticIrrigationStatus
        */
) {
}
