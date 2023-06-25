package com.example.lab3_spring.buildings;

import com.example.lab3_spring.hibernate.entities.Building;
import com.example.lab3_spring.hibernate.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> getAllByStreetId(Long streetId);
}
