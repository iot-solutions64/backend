package com.hydrosmart.irrigation.domain.model.aggregates;

import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankNameCommand;
import com.hydrosmart.irrigation.domain.model.commands.PatchWaterTankWaterAmountRemainingCommand;
import com.hydrosmart.irrigation.domain.model.commands.CreateWaterTankCommand;
import com.hydrosmart.irrigation.domain.model.entities.WaterTankStatus;
import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
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

    @NotBlank
    private String name;

    @NotNull
    private Float waterAmountRemaining;

    @NotNull
    private Float maxWaterCapacity;

    @ManyToOne
    @JoinColumn(name = "status_id", nullable = false)
    private WaterTankStatus status;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public WaterTank(CreateWaterTankCommand command, WaterTankStatus status, User user) {
        this.name = command.name();
        this.waterAmountRemaining = command.waterAmountRemaining();
        this.maxWaterCapacity = command.maxWaterCapacity();
        this.status = status;
        this.user = user;
    }

    public WaterTank patchName(PatchWaterTankNameCommand command) {
        this.name = command.name();
        return this;
    }

    public WaterTank patchWaterAmount(PatchWaterTankWaterAmountRemainingCommand command) {
        this.waterAmountRemaining = command.waterAmountRemaining();
        return this;
    }

    public WaterTank patchStatus(WaterTankStatus status) {
        this.status = status;
        return this;
    }
}
