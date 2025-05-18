package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydrosmart.irrigation.domain.model.entities.WaterTankWaterAmountStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankWaterAmountStatusList;

@Repository
public interface WaterTankWaterAmountStatusRepository extends JpaRepository<WaterTankWaterAmountStatus, Long> {
    Optional<WaterTankWaterAmountStatus> findByName(WaterTankWaterAmountStatusList status);

    boolean existsByName(WaterTankWaterAmountStatusList status);

}
