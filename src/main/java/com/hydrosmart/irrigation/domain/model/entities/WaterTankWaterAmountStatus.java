package com.hydrosmart.irrigation.domain.model.entities;

import com.hydrosmart.irrigation.domain.model.valueobjects.WaterTankWaterAmountStatusList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "water_tank_water_amount_status")
public class WaterTankWaterAmountStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private WaterTankWaterAmountStatusList name;

    public WaterTankWaterAmountStatus(WaterTankWaterAmountStatusList name) {
        this.name = name;
    }

    public String getWaterTankAmountStatusName() {
        return name.name();
    }
}
