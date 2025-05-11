package com.hydrosmart.soil.interfaces.rest.resources;

/**
 * <h3>Create Crop Resource</h3>
 * <p>This resource is used to create a crop</p>
 * @param userId The id of the user owner of the crop
 */
public record CreateCropResource(Long userId) {
}
