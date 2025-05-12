package com.hydrosmart.soil.infrastructure.persistence.jpa.repositories;

import com.hydrosmart.soil.domain.model.entities.TemperatureStatus;
import com.hydrosmart.soil.domain.model.valueobjects.TemperatureStatusList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TemperatureStatusRepository extends JpaRepository<TemperatureStatus, Long> {
    Optional<TemperatureStatus> findByName(TemperatureStatusList status);
    boolean existsByName(TemperatureStatusList status);
}
