package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hydrosmart.irrigation.domain.model.entities.AutomaticIrrigationStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.AutomaticIrrigationStatusList;

public interface AutomaticIrrigationStatusRepository extends JpaRepository<AutomaticIrrigationStatus, Long> {
    Optional<AutomaticIrrigationStatus> findByName(AutomaticIrrigationStatusList status);

    boolean existsByName(AutomaticIrrigationStatusList status);
}
