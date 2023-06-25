package com.example.lab4.dto;

import com.example.lab4.hibernate.entities.BuildingType;
import com.example.lab4.hibernate.entities.Street;
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
