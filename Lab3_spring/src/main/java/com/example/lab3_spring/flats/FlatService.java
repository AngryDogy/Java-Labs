package com.example.lab3_spring.flats;

import com.example.lab3_spring.dto.FlatDto;
import com.example.lab3_spring.hibernate.entities.Flat;
import com.example.lab3_spring.hibernate.entities.Street;

import java.util.List;

public interface FlatService {
    FlatDto saveFlat(FlatDto flatDto);
    List<FlatDto> fetchFlatList();
    FlatDto updateFlat(FlatDto flat, Long flatId);
    void deleteFlatById(Long flatId);
    List<FlatDto> getAllByBuildingId(Long buildingId);
}
