package com.hydrosmart.soil.infrastructure.persistence.jpa.repositories;

import com.hydrosmart.soil.domain.model.entities.HumidityStatus;
import com.hydrosmart.soil.domain.model.valueobjects.HumidityStatusList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumidityStatusRepository extends JpaRepository<HumidityStatus, Long> {
    Optional<HumidityStatus> findByName(HumidityStatusList status);
    boolean existsByName(HumidityStatusList status);
}
