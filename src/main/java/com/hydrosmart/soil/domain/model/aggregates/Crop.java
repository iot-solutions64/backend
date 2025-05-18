package com.hydrosmart.soil.domain.model.aggregates;

import com.hydrosmart.security.domain.model.aggregates.User;
import com.hydrosmart.soil.domain.model.commands.CreateCropCommand;
import com.hydrosmart.soil.domain.model.entities.Humidity;
import com.hydrosmart.soil.domain.model.entities.Temperature;
import com.hydrosmart.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
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

    @Size(max = 50)
    private String name;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "temperature_id", nullable = false)
    private Temperature temperature;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "humidity_id", nullable = false)
    private Humidity humidity;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public Crop(Temperature temperature, Humidity humidity, User user) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.user = user;
    }

    public Crop(CreateCropCommand command, Temperature temperature, Humidity humidity, User user){
        this.name = command.name();
        this.temperature = temperature;
        this.humidity = humidity;
        this.user = user;
    }
}
