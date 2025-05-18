package com.hydrosmart.soil.infrastructure.persistence.jpa.repositories;

import com.hydrosmart.soil.domain.model.entities.Humidity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HumidityRepository extends JpaRepository<Humidity, Long> {
}
