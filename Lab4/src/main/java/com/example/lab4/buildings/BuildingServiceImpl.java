package com.example.lab4.buildings;

import com.example.lab4.dto.BuildingDto;
import com.example.lab4.hibernate.entities.Building;
import com.example.lab4.mapper.BuildingMapper;
import com.example.lab4.tools.DatabaseEntityDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    @Override
    public BuildingDto saveBuilding(BuildingDto buildingDto) {
        Building building = convertToEntity(buildingDto);
        buildingRepository.save(building);
        return buildingDto;
    }

    @Override
    public List<BuildingDto> fetchBuildingList() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingDto updateBuilding(BuildingDto buildingDto, Long buildingId) {
        Building building = convertToEntity(buildingDto);
        Building buildingDB = buildingRepository.findById(buildingId)
                .orElseThrow(() -> new DatabaseEntityDoesNotExistException("Can't find this record in the database"));
        buildingDB.setName(building.getName());
        buildingDB.setConstructionDate(building.getConstructionDate());
        buildingDB.setFloorsNumber(building.getFloorsNumber());
        buildingDB.setBuildingType(building.getBuildingType());
        buildingDB.setStreet(building.getStreet());
        buildingRepository.save(buildingDB);
        return buildingDto;
    }

    @Override
    public void deleteBuildingById(Long buildingId) {
        buildingRepository.deleteById(buildingId);
    }

    @Override
    public List<BuildingDto> getAllByStreetId(Long streetId) {
        List<Building> buildings = buildingRepository.getAllByStreetId(streetId);
        return buildings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private BuildingDto convertToDto(Building building) {
        return buildingMapper.mapToDto(building);
    }
    private Building convertToEntity(BuildingDto buildingDto) {
        return buildingMapper.mapToEntity(buildingDto);
    }
    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
    }
}
