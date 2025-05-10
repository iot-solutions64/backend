package com.hydrosmart.soil.domain.model.aggregates;

import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "crops")
public class Crop extends AuditableAbstractAggregateRoot<Crop> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "temperature_id", nullable = false)
    private Temperature temperatureId;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "humidity_id", nullable = false)
    private Humidity humidityId;

    Crop(CreateCropCommand command){

    }
}
