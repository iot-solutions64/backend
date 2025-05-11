package com.hydrosmart.soil.domain.model.entities;

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

    public Temperature(TemperatureStatus temperatureStatus){
        this.temperature = 0;
        this.temperatureMinThreshold = 0;
        this.temperatureMaxThreshold = 100;
        this.temperatureStatus = temperatureStatus;
        this.temperatureSuggestedActions = "";
    }
}
