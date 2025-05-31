package com.hydrosmart.irrigation.domain.model.aggregates;

import com.hydrosmart.irrigation.domain.model.commands.UpdateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterAmountStatus;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.security.domain.model.aggregates.User;
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
    private Float waterAmountRemaining;

    @NotNull
    private Float maxWaterCapacity;

    @ManyToOne
    @JoinColumn(name = "water_amount_status", nullable = false)
    private WaterAmountStatus waterAmountStatus;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private WaterTankStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public WaterTank(
            CreateWaterTankCommand command,
            WaterAmountStatus waterAmountStatus,
            WaterTankStatus status,
            User user
    ) {
        this.waterAmountRemaining = command.waterAmountRemaining();
        this.maxWaterCapacity = command.maxWaterCapacity();
        this.waterAmountStatus = waterAmountStatus;
        this.status = status;
        this.user = user;
    }

    public WaterTank updateWaterTank(
            UpdateWaterTankCommand command,
            WaterAmountStatus waterAmountStatus,
            WaterTankStatus waterTankStatus) {
        this.waterAmountRemaining = command.waterAmountRemaining();
        this.maxWaterCapacity = command.maxWaterCapacity();
        this.waterAmountStatus = waterAmountStatus;
        this.status = waterTankStatus;
        return this;
    }

}
