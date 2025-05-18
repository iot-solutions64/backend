package com.hydrosmart.irrigation.domain.model.agregates;

import com.hydrosmart.irrigation.domain.model.entities.WaterTankWaterAmountStatus;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "water_tanks")
public class WaterTank extends AuditableAbstractAggregateRoot<WaterTank> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Float waterTankWaterAmountRemaning;

    @NotNull
    private Float waterTankMaxWaterCapacity;

    @ManyToOne
    @JoinColumn(name = "water_tank_water_amount_status", nullable = false)
    private WaterTankWaterAmountStatus waterTankWaterAmountStatus;

    @ManyToOne
    @JoinColumn(name = "water_tank_status", nullable = false)
    private WaterTankStatus waterTankStatus;

    @NotNull
    private String waterTankQuality;

    public WaterTank(Float waterTankWaterAmountRemaning, Float waterTankMaxWaterCapacity,
            WaterTankWaterAmountStatus waterTankWaterAmountStatus, WaterTankStatus waterTankStatus,
            String waterTankQuality) {
        this.waterTankWaterAmountRemaning = waterTankWaterAmountRemaning;
        this.waterTankMaxWaterCapacity = waterTankMaxWaterCapacity;
        this.waterTankWaterAmountStatus = waterTankWaterAmountStatus;
        this.waterTankStatus = waterTankStatus;
        this.waterTankQuality = waterTankQuality;
    }

    public WaterTank(
            CreateWaterTankCommand command,
            WaterTankWaterAmountStatus waterTankWaterAmountStatus,
            WaterTankStatus waterTankStatus) {
        this.waterTankWaterAmountRemaning = command.waterTankWaterAmountRemaning();
        this.waterTankMaxWaterCapacity = command.waterTankMaxWaterCapacity();
        this.waterTankWaterAmountStatus = waterTankWaterAmountStatus;
        this.waterTankStatus = waterTankStatus;
        this.waterTankQuality = command.waterTankQuality();
    }

    public WaterTank updateWaterTank(
            CreateWaterTankCommand command,
            WaterTankWaterAmountStatus waterTankWaterAmountStatus,
            WaterTankStatus waterTankStatus) {
        this.waterTankWaterAmountRemaning = command.waterTankWaterAmountRemaning();
        this.waterTankMaxWaterCapacity = command.waterTankMaxWaterCapacity();
        this.waterTankWaterAmountStatus = waterTankWaterAmountStatus;
        this.waterTankStatus = waterTankStatus;
        this.waterTankQuality = command.waterTankQuality();
        return this;
    }

}
