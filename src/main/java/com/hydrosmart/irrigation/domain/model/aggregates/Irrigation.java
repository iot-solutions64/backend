package com.hydrosmart.irrigation.domain.model.aggregates;

import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationFrequency;
import com.hydrosmart.irrigation.domain.model.entities.IrrigationStatus;
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
@Table(name = "irrigations")
public class Irrigation extends AuditableAbstractAggregateRoot<Irrigation> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private IrrigationFrequency frequency;

    @ManyToOne
    @JoinColumn(name = "status", nullable = false)
    private IrrigationStatus irrigationStatus;

    @NotNull
    private Float maxWaterUsage;

    public Irrigation(CreateIrrigationCommand command, IrrigationStatus irrigationStatus, IrrigationFrequency frequency) {
        this.irrigationStatus = irrigationStatus;
        this.maxWaterUsage = command.maxWaterUsage();
        this.frequency = frequency;
    }

    public Irrigation updateIrrigation(UpdateIrrigationCommand command, IrrigationStatus irrigationStatus, IrrigationFrequency frequency) {
        this.irrigationStatus = irrigationStatus;
        this.maxWaterUsage = command.maxWaterUsage();
        this.frequency = frequency;
        return this;
    }
}
