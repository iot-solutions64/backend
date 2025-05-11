package com.hydrosmart.soil.domain.model.entities;

import com.hydrosmart.soil.domain.model.commands.CreateHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.PatchHumidityCommand;
import com.hydrosmart.soil.domain.model.commands.UpdateHumidityCommand;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Humidity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private float humidity;

    @NotBlank
    private float humidityMinThreshold;

    @NotBlank
    private float humidityMaxThreshold;

    @ManyToOne
    @JoinColumn(name = "humidity_status", nullable = false)
    private HumidityStatus humidityStatus;

    public Humidity(CreateHumidityCommand command, HumidityStatus humidityStatus){
        this.humidity = command.humidity();
        this.humidityMinThreshold = command.humidityMinThreshold();
        this.humidityMaxThreshold = command.humidityMaxThreshold();
        this.humidityStatus = humidityStatus;
    }

    public Humidity updateHumidity(UpdateHumidityCommand command, HumidityStatus humidityStatus){
        this.humidity = command.humidity();
        this.humidityMinThreshold = command.humidityMinThreshold();
        this.humidityMaxThreshold = command.humidityMaxThreshold();
        this.humidityStatus = humidityStatus;
        return this;
    }

    public Humidity patchHumidity(PatchHumidityCommand command, HumidityStatus humidityStatus){
        this.humidity = command.humidity();
        this.humidityStatus = humidityStatus;
        return this;
    }
}
