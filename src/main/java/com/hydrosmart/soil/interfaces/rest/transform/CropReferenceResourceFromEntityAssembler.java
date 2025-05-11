package com.hydrosmart.soil.interfaces.rest.transform;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.interfaces.rest.resources.CropReferenceResource;

public class CropReferenceResourceFromEntityAssembler {
    public static CropReferenceResource toResourceFromEntity(Crop entity){
        return new CropReferenceResource(
                entity.getId(),
                entity.getUser().getId(),
                entity.getTemperature().getId(),
                entity.getHumidity().getId()
        );
    }
}
