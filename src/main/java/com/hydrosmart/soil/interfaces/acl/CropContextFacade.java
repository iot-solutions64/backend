package com.hydrosmart.soil.interfaces.acl;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.queries.GetAllCropsByWaterTankIdQuery;
import com.hydrosmart.soil.domain.services.queryservices.CropQueryService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CropContextFacade {
    private final CropQueryService cropQueryService;
    private final CropRepository cropRepository;
    public CropContextFacade(CropQueryService cropQueryService, CropRepository cropRepository) {
        this.cropQueryService = cropQueryService;
        this.cropRepository = cropRepository;
    }

    public List<Crop> getCropsByWaterTankId(Long waterTankId) {
        var getAllCropsByWaterTankIdQuery = new GetAllCropsByWaterTankIdQuery(waterTankId);
        return cropQueryService.handle(getAllCropsByWaterTankIdQuery);
    }

    public void saveCrops(List<Crop> crops) {
        cropRepository.saveAll(crops);
    }
}
