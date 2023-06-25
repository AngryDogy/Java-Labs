package com.example.models.mapper;


import com.example.models.dto.BuildingDto;
import com.example.models.entities.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {
    private final StreetMapper streetMapper;

    public BuildingDto mapToDto(Building building) {
        if (building == null) {
            return null;
        }
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setId(building.getId());
        buildingDto.setName(building.getName());
        buildingDto.setFloorsNumber(building.getFloorsNumber());
        buildingDto.setConstructionDate(building.getConstructionDate());
        buildingDto.setMaterial(building.getMaterial());
        buildingDto.setBuildingType(building.getBuildingType());
        buildingDto.setStreetDto(streetMapper.mapToDto(building.getStreet()));
        return buildingDto;
    }

    public Building mapToEntity(BuildingDto buildingDto) {
        if (buildingDto == null) {
            return null;
        }
        Building building = new Building();
        building.setId(buildingDto.getId());
        building.setName(buildingDto.getName());
        building.setFloorsNumber(buildingDto.getFloorsNumber());
        building.setConstructionDate(buildingDto.getConstructionDate());
        building.setMaterial(buildingDto.getMaterial());
        building.setBuildingType(buildingDto.getBuildingType());
        building.setStreet(streetMapper.mapToEntity(buildingDto.getStreetDto()));
        return building;
    }

    @Autowired
    public BuildingMapper(StreetMapper streetMapper) {
        this.streetMapper = streetMapper;
    }
}
