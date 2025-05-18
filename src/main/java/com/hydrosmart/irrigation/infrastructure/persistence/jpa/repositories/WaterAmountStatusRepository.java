package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydrosmart.irrigation.domain.model.entities.WaterAmountStatus;
import com.hydrosmart.irrigation.domain.model.valueobjects.WaterAmountStatusList;

@Repository
public interface WaterAmountStatusRepository extends JpaRepository<WaterAmountStatus, Long> {
    Optional<WaterAmountStatus> findByName(WaterAmountStatusList status);

    boolean existsByName(WaterAmountStatusList status);

}
