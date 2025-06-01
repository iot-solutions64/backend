package com.hydrosmart.soil.interfaces.rest.resources;

import java.time.LocalTime;

/**
 * <h3>Crop Light Resource</h3>
 * <p>This resource returns the most important data of the classes related to the crop</p>
 * @param cropId The id of the crop
 * @param cropName The name of the crop
 * @param userId The id of the user owner of the crop
 * @param temperature The current temperature of the crop
 * @param humidity The current humidity of the crop
 * @param waterAmountRemaining The amount of water remaining in the water tank related to the crop
 * @param irrigationStartTime The start time of the automatic irrigation
 * @param irrigationDurationInMinutes The durationInMinutes of the automatic irrigation (in minutes)
 * @param irrigationStatus The status of the irrigation
 */
public record CropLightResource(
        //Crop Data
        Long cropId,
        String cropName,
        Long userId,
        //Temperature Data
        Float temperature,
        //Humidity Data
        Float humidity,
        //Water Tank Data
        Float waterAmountRemaining,
        //Irrigation Data
        LocalTime irrigationStartTime,
        int irrigationDurationInMinutes,
        String irrigationStatus
) {
}
