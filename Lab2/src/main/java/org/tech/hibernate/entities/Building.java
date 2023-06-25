package org.tech.hibernate.entities;


import jakarta.persistence.*;
import lombok.Data;
import jakarta.persistence.Table;
import java.util.Calendar;

@Data
@Entity
@Table(name = "buildings", schema = "city")
public class Building {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "date_of_construction", nullable = false)
    private Calendar constructionDate;
    @Column(name = "number_of_floors", nullable = false)
    private int floorsNumber;

    @Column(name = "building_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @ManyToOne
    @JoinColumn(name = "street", referencedColumnName = "id")
    private Street street;
    public Building() {

    }

    public Building(String name, Calendar date, int floorsNumber, BuildingType buildingType, Street street) {
        this.name = name;
        this.constructionDate = date;
        this.floorsNumber = floorsNumber;
        this.buildingType = buildingType;
        this.street = street;
    }
}
