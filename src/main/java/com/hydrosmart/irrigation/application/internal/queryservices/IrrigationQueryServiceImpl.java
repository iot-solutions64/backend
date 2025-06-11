package com.hydrosmart.irrigation.application.internal.queryservices;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;
import com.hydrosmart.irrigation.domain.model.queries.GetIrrigationByIdQuery;
import com.hydrosmart.irrigation.domain.services.queryservices.IrrigationQueryService;
import com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories.IrrigationRepository;

@Service
public class IrrigationQueryServiceImpl implements IrrigationQueryService {
    private final IrrigationRepository irrigationRepository;

    public IrrigationQueryServiceImpl(IrrigationRepository irrigationRepository) {
        this.irrigationRepository = irrigationRepository;
    }

    @Override
    public Optional<Irrigation> handle(GetIrrigationByIdQuery query) {
        return irrigationRepository.findById(query.irrigationId());
    }
}
