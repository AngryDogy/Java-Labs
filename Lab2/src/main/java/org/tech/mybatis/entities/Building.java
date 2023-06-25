package org.tech.mybatis.entities;

import lombok.Data;
import java.sql.Date;

@Data
public class Building {
    private long id;

    private String name;

    private Date constructionDate;

    private int floorsNumber;

    private BuildingType buildingType;

    private long street;
    public Building(long id, String name, Date date, int floorsNumber, BuildingType buildingType, long street) {
        this.id = id;
        this.name = name;
        this.constructionDate = date;
        this.floorsNumber = floorsNumber;
        this.buildingType = buildingType;
        this.street = street;
    }
}
