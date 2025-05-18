package com.hydrosmart.irrigation.domain.model.agregates;

import com.hydrosmart.irrigation.domain.model.commands.CreateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.commands.UpdateIrrigationCommand;
import com.hydrosmart.irrigation.domain.model.entities.AutomaticIrrigationStatus;
import com.hydrosmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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

    @ManyToOne
    @JoinColumn(name = "automatic_irrigation_status", nullable = false)
    private AutomaticIrrigationStatus automaticIrrigationStatus;

    @NotNull
    Float automaticIrrigationMaxWaterUsage;

    @NotNull
    Float automaticIrrigationDefaultMaxWaterUsage;

    @NotNull
    List<LocalDateTime[]> automaticIrrigationOperationHours;

    @NotNull
    String automaticIrrigationSuggestedActions;

    public Irrigation(
            AutomaticIrrigationStatus automaticIrrigationStatus,
            Float automaticIrrigationMaxWaterUsage,
            Float automaticIrrigationDefaultMaxWaterUsage,
            List<LocalDateTime[]> automaticIrrigationOperationHours,
            String automaticIrrigationSuggestedActions) {
        this.automaticIrrigationStatus = automaticIrrigationStatus;
        this.automaticIrrigationMaxWaterUsage = automaticIrrigationMaxWaterUsage;
        this.automaticIrrigationDefaultMaxWaterUsage = automaticIrrigationDefaultMaxWaterUsage;
        this.automaticIrrigationOperationHours = automaticIrrigationOperationHours;
        this.automaticIrrigationSuggestedActions = automaticIrrigationSuggestedActions;
    }

    public Irrigation(CreateIrrigationCommand command, AutomaticIrrigationStatus automaticIrrigationStatus) {
        this.automaticIrrigationStatus = automaticIrrigationStatus;
        this.automaticIrrigationMaxWaterUsage = command.automaticIrrigationMaxWaterUsage();
        this.automaticIrrigationDefaultMaxWaterUsage = command.automaticIrrigationDefaultMaxWaterUsage();
        this.automaticIrrigationOperationHours = command.automaticIrrigationOperationHours();
        this.automaticIrrigationSuggestedActions = command.automaticIrrigationSuggestedActions();
    }

    public Irrigation updateIrrigation(
            UpdateIrrigationCommand command,
            AutomaticIrrigationStatus automaticIrrigationStatus) {
        this.automaticIrrigationStatus = automaticIrrigationStatus;
        this.automaticIrrigationMaxWaterUsage = command.automaticIrrigationMaxWaterUsage();
        this.automaticIrrigationDefaultMaxWaterUsage = command.automaticIrrigationDefaultMaxWaterUsage();
        this.automaticIrrigationOperationHours = command.automaticIrrigationOperationHours();
        this.automaticIrrigationSuggestedActions = command.automaticIrrigationSuggestedActions();
        return this;
    }

}
