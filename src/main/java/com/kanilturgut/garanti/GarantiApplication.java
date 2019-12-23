package com.kanilturgut.garanti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GarantiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GarantiApplication.class, args);
    }

}
