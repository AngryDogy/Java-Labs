package com.example.lab3_spring;

import com.example.lab3_spring.dto.BuildingDto;
import com.example.lab3_spring.dto.FlatDto;
import com.example.lab3_spring.dto.StreetDto;
import com.example.lab3_spring.hibernate.entities.Building;
import com.example.lab3_spring.hibernate.entities.BuildingType;
import com.example.lab3_spring.hibernate.entities.Flat;
import com.example.lab3_spring.hibernate.entities.Street;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class Lab3SpringApplicationTests {

    static final ModelMapper modelMapper = new ModelMapper();
    @Test
    void checkStreetMapper() {
        StreetDto streetDto = new StreetDto();
        streetDto.setId(1);
        streetDto.setName("Main");
        streetDto.setPostcode(45612);
        Street street = modelMapper.map(streetDto, Street.class);
        assertEquals(street.getId(), streetDto.getId());
        assertEquals(street.getName(), streetDto.getName());
        assertEquals(street.getPostcode(), streetDto.getPostcode());
    }
    @Test
    void checkBuildingMapper() {
        StreetDto streetDto = new StreetDto();
        streetDto.setId(1);
        streetDto.setName("Main");
        streetDto.setPostcode(45612);
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setId(1);
        buildingDto.setName("Lahta");
        buildingDto.setBuildingType(BuildingType.Garage);
        buildingDto.setMaterial("Wood");
        buildingDto.setFloorsNumber(10);
        buildingDto.setConstructionDate(OffsetDateTime.parse("2011-12-03T10:15:30+01:00"));
        buildingDto.setStreetDto(streetDto);
        Building building = modelMapper.map(buildingDto, Building.class);
        assertEquals(building.getId(), buildingDto.getId());
        assertEquals(building.getName(), buildingDto.getName());
        assertEquals(building.getFloorsNumber(), buildingDto.getFloorsNumber());
        assertEquals(building.getConstructionDate(), building.getConstructionDate());
        assertEquals(building.getMaterial(), buildingDto.getMaterial());
    }
    @Test
    void checkFlatMapper() {
        StreetDto streetDto = new StreetDto();
        streetDto.setId(1);
        streetDto.setName("Main");
        streetDto.setPostcode(45612);
        BuildingDto buildingDto = new BuildingDto();
        buildingDto.setId(1);
        buildingDto.setName("Lahta");
        buildingDto.setBuildingType(BuildingType.Garage);
        buildingDto.setMaterial("Wood");
        buildingDto.setFloorsNumber(10);
        buildingDto.setConstructionDate(OffsetDateTime.parse("2011-12-03T10:15:30+01:00"));
        buildingDto.setStreetDto(streetDto);
        FlatDto flatDto = new FlatDto();
        flatDto.setBuildingDto(buildingDto);
        flatDto.setId(1);
        flatDto.setSquare(100);
        flatDto.setNumber(10);
        flatDto.setRoomsNumber(10);
        Flat flat = modelMapper.map(flatDto, Flat.class);
        assertEquals(flat.getId(), flatDto.getId());
        assertEquals(flat.getSquare(), flatDto.getSquare());
        assertEquals(flat.getRoomsNumber(), flatDto.getRoomsNumber());
        assertEquals(flat.getNumber(), flatDto.getNumber());
    }

}
