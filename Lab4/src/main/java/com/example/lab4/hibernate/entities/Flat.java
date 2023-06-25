package com.example.lab4.hibernate.entities;


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
    private Long id;

    @Column(name = "number", nullable = false)
    private Integer number;

    @Column(name = "square", nullable = false)
    private Integer square;

    @Column(name = "rooms_number", nullable = false)
    private Integer roomsNumber;

    @ManyToOne
    @JoinColumn(name = "building", referencedColumnName = "id")
    private Building building;

    @ManyToOne
    @JoinColumn(name = "owner", referencedColumnName = "login")
    private User user;


}
