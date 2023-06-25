package com.example.models.dto;



import com.example.models.entities.BuildingType;
import lombok.Data;

import java.sql.Date;
import java.time.OffsetDateTime;

@Data
public class BuildingDto {
    private long id;

    private String name;

    private Date constructionDate;

    private int floorsNumber;

    private BuildingType buildingType;

    private StreetDto streetDto;

    private String material;
}
