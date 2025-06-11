package com.hydrosmart.irrigation.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hydrosmart.irrigation.domain.model.aggregates.Irrigation;

@Repository
public interface IrrigationRepository extends JpaRepository<Irrigation, Long> {
}
