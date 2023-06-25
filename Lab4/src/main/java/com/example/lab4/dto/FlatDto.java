package com.example.lab4.dto;

import com.example.lab4.hibernate.entities.Building;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class FlatDto {
    private long id;

    private int number;

    private int square;

    private int roomsNumber;

    private BuildingDto buildingDto;

    private UserDto userDto;
}
