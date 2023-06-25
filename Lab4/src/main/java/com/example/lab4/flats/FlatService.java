package com.example.lab4.flats;

import com.example.lab4.dto.FlatDto;
import com.example.lab4.hibernate.entities.Flat;
import com.example.lab4.hibernate.entities.Street;

import java.util.List;

public interface FlatService {
    FlatDto saveFlat(FlatDto flatDto);
    List<FlatDto> fetchFlatList();
    FlatDto updateFlat(FlatDto flat, Long flatId);
    void deleteFlatById(Long flatId);
    List<FlatDto> getAllByBuildingId(Long buildingId);
}
