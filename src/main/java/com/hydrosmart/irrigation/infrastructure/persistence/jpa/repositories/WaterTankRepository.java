package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydrosmart.irrigation.domain.model.aggregates.WaterTank;

import java.util.List;

@Repository
public interface WaterTankRepository extends JpaRepository<WaterTank, Long> {
    List<WaterTank> getWaterTanksByUserId(Long userId);
}
