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

    private String humiditySuggestedActions;

    public Humidity(HumidityStatus humidityStatus){
        this.humidity = 0;
        this.humidityMinThreshold = 0;
        this.humidityMaxThreshold = 100;
        this.humidityStatus = humidityStatus;
        this.humiditySuggestedActions = "";
    }
}
