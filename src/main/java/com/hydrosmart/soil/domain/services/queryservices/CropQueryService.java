package com.hydrosmart.soil.domain.services.queryservices;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.queries.GetCropByIdQuery;
import com.hydrosmart.soil.domain.model.queries.GetAllCropsByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface CropQueryService {
    Optional<Crop> handle(GetCropByIdQuery query);
    List<Crop> handle(GetAllCropsByUserIdQuery query);
}
