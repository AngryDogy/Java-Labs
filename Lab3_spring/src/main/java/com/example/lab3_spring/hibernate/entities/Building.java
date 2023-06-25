package com.example.lab3_spring.hibernate.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.OffsetDateTime;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private OffsetDateTime constructionDate;
    @Column(name = "number_of_floors", nullable = false)
    private int floorsNumber;

    @Column(name = "building_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    @ManyToOne
    @JoinColumn(name = "street", referencedColumnName = "id")
    private Street street;

    @Column(name = "material")
    private String material;

}
