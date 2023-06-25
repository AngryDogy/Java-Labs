package com.example.lab3_spring.dto;

import com.example.lab3_spring.hibernate.entities.Building;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class FlatDto {
    private long id;

    private int number;

    private int square;

    private int roomsNumber;

    private BuildingDto buildingDto;
}
