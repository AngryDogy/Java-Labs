package com.example.lab3_spring.flats;

import com.example.lab3_spring.hibernate.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    List<Flat> getAllByBuildingId(Long buildingId);
}
