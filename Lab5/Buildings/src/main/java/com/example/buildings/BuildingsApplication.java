package com.example.buildings;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.models"})
public class BuildingsApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuildingsApplication.class, args);
    }

}
