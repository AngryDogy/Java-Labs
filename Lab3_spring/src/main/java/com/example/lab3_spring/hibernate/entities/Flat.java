package com.example.lab3_spring.hibernate.entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flats", schema = "city")
public class Flat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "number", nullable = false)
    private int number;

    @Column(name = "square", nullable = false)
    private int square;

    @Column(name = "rooms_number", nullable = false)
    private int roomsNumber;

    @ManyToOne
    @JoinColumn(name = "building", referencedColumnName = "id")
    private Building building;

}
