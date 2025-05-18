package com.hydrosmart.irrigation.domain.model.entities;

import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankStatusList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "water_tank_status")
public class WaterTankStatus {
    @Id
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private WaterTankStatusList name;

    public WaterTankStatus(WaterTankStatusList name) {
        this.name = name;
    }

    public String getWaterTankStatusName(){
        return name.name();
    }
}
