package com.hydrosmart.soil.domain.model.entities;

import com.hydrosmart.soil.domain.model.valueobjects.HumidityStatusList;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "humidity_status")
public class HumidityStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private HumidityStatusList name;

    public HumidityStatus(HumidityStatusList name) {
        this.name = name;
    }

    public String getTemperatureStatusName(){
        return name.name();
    }
}
