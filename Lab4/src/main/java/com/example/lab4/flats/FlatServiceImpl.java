package com.example.lab4.flats;

import com.example.lab4.dto.FlatDto;
import com.example.lab4.hibernate.entities.Flat;
import com.example.lab4.mapper.FlatMapper;
import com.example.lab4.tools.DatabaseEntityDoesNotExistException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlatServiceImpl implements FlatService {
    private final FlatRepository flatRepository;
    private final FlatMapper flatMapper;

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
        Flat flatDB = flatRepository.findById(flatId)
                .orElseThrow(() -> new DatabaseEntityDoesNotExistException("Can't find this record in the database"));
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
        return flatMapper.mapToDto(flat);
    }
    private Flat convertToEntity(FlatDto flatDto) {
       return flatMapper.mapToEntity(flatDto);
    }

    public FlatServiceImpl(FlatRepository flatRepository, FlatMapper flatMapper) {
        this.flatRepository = flatRepository;
        this.flatMapper = flatMapper;
    }
}
