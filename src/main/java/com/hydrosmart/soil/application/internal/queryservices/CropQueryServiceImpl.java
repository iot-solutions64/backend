package com.hydrosmart.soil.application.internal.queryservices;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import com.hydrosmart.soil.domain.model.queries.GetCropByIdQuery;
import com.hydrosmart.soil.domain.model.queries.GetAllCropsByUserIdQuery;
import com.hydrosmart.soil.domain.services.queryservices.CropQueryService;
import com.hydrosmart.soil.infrastructure.persistence.jpa.repositories.CropRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CropQueryServiceImpl implements CropQueryService {
    private final CropRepository cropRepository;

    public CropQueryServiceImpl(CropRepository cropRepository) {
        this.cropRepository = cropRepository;
    }

    @Override
    public Optional<Crop> handle(GetCropByIdQuery query) {
        return cropRepository.findById(query.cropId());
    }

    @Override
    public List<Crop> handle(GetAllCropsByUserIdQuery query) {
        return cropRepository.findByUserId(query.userId());
    }
}
