package com.example.lab4.buildings;

import com.example.lab4.dto.BuildingDto;

import java.util.List;

public interface BuildingService {
    BuildingDto saveBuilding(BuildingDto buildingDto);
    List<BuildingDto> fetchBuildingList();
    BuildingDto updateBuilding(BuildingDto buildingDto, Long buildingId);
    void deleteBuildingById(Long buildingId);
    List<BuildingDto> getAllByStreetId(Long streetId);
}
