package com.example.lab3_spring.flats;

import com.example.lab3_spring.dto.FlatDto;
import com.example.lab3_spring.hibernate.entities.Flat;
import com.example.lab3_spring.tools.DatabaseEntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatServiceImpl implements FlatService {
    private final FlatRepository flatRepository;
    private final ModelMapper modelMapper;

    @Override
    public FlatDto saveFlat(FlatDto flatDto) {
        Flat flat = convertToEntity(flatDto);
        flatRepository.save(flat);
        return flatDto;
    }

    @Override
    public List<FlatDto> fetchFlatList() {
        List<Flat> flats = flatRepository.findAll();
        return flats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FlatDto updateFlat(FlatDto flatDto, Long flatId) {
        Flat flat = convertToEntity(flatDto);
        Flat flatDB = flatRepository.findById(flatId).get();
        if (flatDB == null) {
            throw new DatabaseEntityDoesNotExistException("Can't find this record in database");
        }
        flatDB.setNumber(flat.getNumber());
        flatDB.setSquare(flat.getSquare());
        flatDB.setRoomsNumber(flat.getRoomsNumber());
        flatDB.setBuilding(flat.getBuilding());
        flatRepository.save(flatDB);
        return flatDto;
    }

    @Override
    public void deleteFlatById(Long flatId) {
        flatRepository.deleteById(flatId);
    }

    @Override
    public List<FlatDto> getAllByBuildingId(Long buildingId) {
        List<Flat> flats = flatRepository.getAllByBuildingId(buildingId);
        return flats.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private FlatDto convertToDto(Flat flat) {
        FlatDto flatDto = modelMapper.map(flat, FlatDto.class);
        return flatDto;
    }
    private Flat convertToEntity(FlatDto flatDto) {
        Flat flat = modelMapper.map(flatDto, Flat.class);
        return flat;
    }

    public FlatServiceImpl(FlatRepository flatRepository) {
        this.flatRepository = flatRepository;
        modelMapper = new ModelMapper();
    }
}
