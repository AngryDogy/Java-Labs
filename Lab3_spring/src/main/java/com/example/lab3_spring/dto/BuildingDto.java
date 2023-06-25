package com.example.lab3_spring.dto;

import com.example.lab3_spring.hibernate.entities.BuildingType;
import com.example.lab3_spring.hibernate.entities.Street;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class BuildingDto {
    private long id;

    private String name;

    private OffsetDateTime constructionDate;

    private int floorsNumber;

    private BuildingType buildingType;

    private StreetDto streetDto;

    private String material;
}
