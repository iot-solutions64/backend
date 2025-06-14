package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import com.hydrosmart.irrigation.domain.model.entities.SensorDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorDataRepository extends JpaRepository<SensorDataEntity, Long> {
}
