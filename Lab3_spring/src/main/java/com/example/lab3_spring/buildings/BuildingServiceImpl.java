package com.example.lab3_spring.buildings;

import com.example.lab3_spring.dto.BuildingDto;
import com.example.lab3_spring.dto.StreetDto;
import com.example.lab3_spring.hibernate.entities.Building;
import com.example.lab3_spring.hibernate.entities.Street;
import com.example.lab3_spring.tools.DatabaseEntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    private final BuildingRepository buildingRepository;
    private final ModelMapper modelMapper;

    @Override
    public BuildingDto saveBuilding(BuildingDto buildingDto) {
        Building building = convertToEntity(buildingDto);
        buildingRepository.save(building);
        return buildingDto;
    }

    @Override
    public List<BuildingDto> fetchStreetList() {
        List<Building> buildings = buildingRepository.findAll();
        return buildings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BuildingDto updateBuilding(BuildingDto buildingDto, Long buildingId) {
        Building building = convertToEntity(buildingDto);
        Building buildingDB = buildingRepository.findById(buildingId).get();
        if (buildingDB == null) {
            throw new DatabaseEntityDoesNotExistException("Can't find this record in database");
        }
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
        BuildingDto buildingDto = modelMapper.map(building, BuildingDto.class);
        return buildingDto;
    }
    private Building convertToEntity(BuildingDto buildingDto) {
        Building building = modelMapper.map(buildingDto, Building.class);
        return building;
    }
    private Street convertToEntity(StreetDto streetDto) {
        Street street = modelMapper.map(streetDto, Street.class);
        return street;
    }

    @Autowired
    public BuildingServiceImpl(BuildingRepository buildingRepository) {
        this.buildingRepository = buildingRepository;
        modelMapper = new ModelMapper();
    }
}
