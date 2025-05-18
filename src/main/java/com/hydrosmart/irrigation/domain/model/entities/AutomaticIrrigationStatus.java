package com.hydrosmart.irrigation.domain.model.entities;

import com.hydrosmart.irrigation.domain.model.valueobjects.AutomaticIrrigationStatusList;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "automatic_irrigation_status")
public class AutomaticIrrigationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private AutomaticIrrigationStatusList name;

    public AutomaticIrrigationStatus(AutomaticIrrigationStatusList name) {
        this.name = name;
    }

    public String getAutomaticIrrigationStatusName(){
        return name.name();
    }
}
