package com.hydrosmart.irrigation.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import com.hydrosmart.irrigation.domain.model.queries.GetWaterTanksByUserId;
import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.services.queryservices.WaterTankQueryService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankRepository;

@Service
public class WaterTankQueryServiceImpl implements WaterTankQueryService {
    private final WaterTankRepository waterTankRepository;

    public WaterTankQueryServiceImpl(WaterTankRepository waterTankRepository) {
        this.waterTankRepository = waterTankRepository;
    }

    @Override
    public Optional<WaterTank> handle(GetWaterTankByIdQuery query) {
        return waterTankRepository.findById(query.waterTankId());
    }

    @Override
    public List<WaterTank> handle(GetWaterTanksByUserId query) {
        return waterTankRepository.getWaterTanksByUserId(query.userId());
    }
}
