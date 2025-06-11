package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.IrrigationStatusList;

public interface IrrigationStatusRepository extends JpaRepository<IrrigationStatus, Long> {
    Optional<IrrigationStatus> findByName(IrrigationStatusList status);

    boolean existsByName(IrrigationStatusList status);
}
