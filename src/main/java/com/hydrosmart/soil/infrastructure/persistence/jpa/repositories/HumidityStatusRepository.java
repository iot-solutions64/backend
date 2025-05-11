package com.hydrosmart.soil.infrastructure.persistence.jpa.repositories;

import com.hydrosmart.soil.domain.model.entities.HumidityStatus;
import com.hydrosmart.soil.domain.model.valueobjects.HumidityStatusList;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HumidityStatusRepository {
    Optional<HumidityStatus> findByName(HumidityStatusList status);
    boolean existsByName(HumidityStatusList status);
}
