package com.hydrosmart.irrigation.application.internal.queryservices;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.agregates.WaterTank;
import com.hydrosmart.irrigation.domain.model.queries.GetAllWaterTanksQuery;
import com.hydrosmart.irrigation.domain.model.queries.GetWaterTankByIdQuery;
import com.hydrosmart.irrigation.domain.services.queryservices.WaterTankQueryService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.WaterTankRespository;

@Service
public class WaterTankQueryServiceImpl implements WaterTankQueryService {
    private final WaterTankRespository waterTankRepository;

    public WaterTankQueryServiceImpl(WaterTankRespository waterTankRepository) {
        this.waterTankRepository = waterTankRepository;
    }

    @Override
    public Optional<WaterTank> handle(GetWaterTankByIdQuery query) {
        return waterTankRepository.findById(query.waterTankId());
    }

    @Override
    public Optional<List<WaterTank>> handle(GetAllWaterTanksQuery query) {
        return Optional.of(waterTankRepository.findAll());
    }
}
