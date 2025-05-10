package com.hydrosmart.irrigation.domain.model.entities;

import com.hydrosmart.irrigation.domain.model.valueobjects.TemperatureStatusList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "temperature_status")
public class TemperatureStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private TemperatureStatusList name;

    public TemperatureStatus(TemperatureStatusList name) {
        this.name = name;
    }

    public String getTemperatureStatusName(){
        return name.name();
    }
}
