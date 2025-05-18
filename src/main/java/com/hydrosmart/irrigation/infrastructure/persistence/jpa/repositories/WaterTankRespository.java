package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydrosmart.irrigation.domain.model.agregates.WaterTank;

@Repository
public interface WaterTankRespository extends JpaRepository<WaterTank, Long> {
}
