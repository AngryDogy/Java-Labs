package com.example.lab4.buildings;

import com.example.lab4.hibernate.entities.Building;
import com.example.lab4.hibernate.entities.Street;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
    List<Building> getAllByStreetId(Long streetId);
}
