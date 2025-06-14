package com.hydrosmart.soil.domain.model.entities;

import com.hydrosmart.soil.domain.model.commands.CreateTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureCommand;
import com.hydrosmart.soil.domain.model.commands.PatchTemperatureThresholdCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    private Float temperature;

    @NotNull
    private Float temperatureMinThreshold;

    @NotNull
    private Float temperatureMaxThreshold;

    @ManyToOne
    @JoinColumn(name = "temperature_status", nullable = false)
    private TemperatureStatus temperatureStatus;

    public Temperature(CreateTemperatureCommand command, TemperatureStatus temperatureStatus){
        this.temperature = command.temperature();
        this.temperatureMinThreshold = command.temperatureMinThreshold();
        this.temperatureMaxThreshold = command.temperatureMaxThreshold();
        this.temperatureStatus = temperatureStatus;
    }

    public Temperature patchTemperatureThreshold(PatchTemperatureThresholdCommand command, TemperatureStatus temperatureStatus){
        this.temperatureMinThreshold = command.temperatureMinThreshold();
        this.temperatureMaxThreshold = command.temperatureMaxThreshold();
        this.temperatureStatus = temperatureStatus;
        return this;
    }

    public Temperature patchTemperature(PatchTemperatureCommand command, TemperatureStatus temperatureStatus){
        this.temperature = command.temperature();
        this.temperatureStatus = temperatureStatus;
        return this;
    }
}
