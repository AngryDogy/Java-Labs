package com.example.lab4.mapper;

import com.example.lab4.dto.FlatDto;
import com.example.lab4.hibernate.entities.Flat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlatMapper {
    private final BuildingMapper buildingMapper;
    private final UserMapper userMapper;

    public FlatDto mapToDto(Flat flat) {
        if (flat == null) {
            return null;
        }
        FlatDto flatDto = new FlatDto();
        flatDto.setId(flat.getId());
        flatDto.setSquare(flat.getSquare());
        flatDto.setNumber(flat.getNumber());
        flatDto.setRoomsNumber(flat.getRoomsNumber());
        flatDto.setBuildingDto(buildingMapper.mapToDto(flat.getBuilding()));
        flatDto.setUserDto(userMapper.mapToDto(flat.getUser()));
        return flatDto;
    }

    public Flat mapToEntity(FlatDto flatDto) {
        if (flatDto == null) {
            return null;
        }
        Flat flat = new Flat();
        flat.setId(flatDto.getId());
        flat.setSquare(flatDto.getSquare());
        flat.setNumber(flatDto.getNumber());
        flat.setRoomsNumber(flatDto.getRoomsNumber());
        flat.setBuilding(buildingMapper.mapToEntity(flatDto.getBuildingDto()));
        flat.setUser(userMapper.mapToEntity(flatDto.getUserDto()));
        return flat;
    }

    @Autowired
    public FlatMapper(BuildingMapper buildingMapper, UserMapper userMapper) {
        this.buildingMapper = buildingMapper;
        this.userMapper = userMapper;
    }
}
