package com.hydrosmart.irrigation.domain.model.entities;

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
    private float humidityThreshold;

    @ManyToOne
    @JoinColumn(name = "humidity_status", nullable = false)
    private HumidityStatus humidityStatus;

    private String humiditySuggestedActions;

    public Humidity(HumidityStatus humidityStatus){
        this.humidity = 0;
        this.humidityThreshold = 100;
        this.humidityStatus = humidityStatus;
        this.humiditySuggestedActions = "";
    }
}
