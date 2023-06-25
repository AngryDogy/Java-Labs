package com.example.lab3_spring.buildings;

import com.example.lab3_spring.dto.BuildingDto;
import com.example.lab3_spring.hibernate.entities.Building;

import java.util.List;

public interface BuildingService {
    BuildingDto saveBuilding(BuildingDto buildingDto);
    List<BuildingDto> fetchStreetList();
    BuildingDto updateBuilding(BuildingDto buildingDto, Long buildingId);
    void deleteBuildingById(Long buildingId);
    List<BuildingDto> getAllByStreetId(Long streetId);
}
