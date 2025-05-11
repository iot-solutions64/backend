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

    public Humidity(HumidityStatus humidityStatus){
        this.humidity = 0;
        this.humidityMinThreshold = 0;
        this.humidityMaxThreshold = 100;
        this.humidityStatus = humidityStatus;
    }

    public Humidity(CreateHumidityCommand command){
        this.humidity = command.humidity();
        this.humidityMinThreshold = command.humidityMinThreshold();
        this.humidityMaxThreshold = command.humidityMaxThreshold();
    }

    public Humidity updateHumidity(UpdateHumidityCommand command){
        this.humidity = command.humidity();
        this.humidityMinThreshold = command.humidityMinThreshold();
        this.humidityMaxThreshold = command.humidityMaxThreshold();
        return this;
    }

    public Humidity patchHumidity(PatchHumidityCommand command){
        this.humidity = command.humidity();
        return this;
    }
}
