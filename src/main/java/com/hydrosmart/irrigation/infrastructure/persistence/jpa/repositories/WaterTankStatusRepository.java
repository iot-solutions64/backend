package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;

@Repository
public interface WaterTankStatusRepository extends JpaRepository<WaterTankStatus, Long> {
    Optional<WaterTankStatus> findByName(WaterTankStatusList status);

    boolean existsByName(WaterTankStatusList status);
}
