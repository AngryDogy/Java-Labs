package com.example.lab4.flats;

import com.example.lab4.hibernate.entities.Flat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlatRepository extends JpaRepository<Flat, Long> {
    List<Flat> getAllByBuildingId(Long buildingId);
}
