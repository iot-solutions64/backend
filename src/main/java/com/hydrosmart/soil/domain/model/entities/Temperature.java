package com.hydrosmart.soil.domain.model.entities;

import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.UpdateTemperatureCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Temperature {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private float temperature;

    @NotBlank
    private float temperatureMinThreshold;

    @NotBlank
    private float temperatureMaxThreshold;

    @ManyToOne
    @JoinColumn(name = "temperature_status", nullable = false)
    private TemperatureStatus temperatureStatus;

    private String temperatureSuggestedActions;

    public Temperature(CreateTemperatureCommand command, TemperatureStatus temperatureStatus){
        this.temperature = command.temperature();
        this.temperatureMinThreshold = command.temperatureMinThreshold();
        this.temperatureMaxThreshold = command.temperatureMaxThreshold();
        this.temperatureStatus = temperatureStatus;
        this.temperatureSuggestedActions = command.temperatureSuggestedActions();
    }

    public Temperature updateTemperature(UpdateTemperatureCommand command, TemperatureStatus temperatureStatus){
        this.temperature = command.temperature();
        this.temperatureMinThreshold = command.temperatureMinThreshold();
        this.temperatureMaxThreshold = command.temperatureMaxThreshold();
        this.temperatureStatus = temperatureStatus;
        this.temperatureSuggestedActions = command.temperatureSuggestedActions();
        return this;
    }

    public Temperature patchTemperature(PatchTemperatureCommand command){
        this.temperature = command.temperature();
        this.temperatureSuggestedActions = command.temperatureSuggestedActions();
        return this;
    }
}
