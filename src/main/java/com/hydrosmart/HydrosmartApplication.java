package com.hydrosmart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HydrosmartApplication {

    public static void main(String[] args) {
        SpringApplication.run(HydrosmartApplication.class, args);
    }

}
