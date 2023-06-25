package com.example.streets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.example.models"})
public class StreetsApplication {

    public static void main(String[] args) {
        SpringApplication.run(StreetsApplication.class, args);
    }

}
