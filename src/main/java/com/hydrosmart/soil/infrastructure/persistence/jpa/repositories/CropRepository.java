package com.hydrosmart.soil.infrastructure.persistence.jpa.repositories;

import com.hydrosmart.soil.domain.model.aggregates.Crop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {
    List<Crop> findByUserId(Long userId);
}