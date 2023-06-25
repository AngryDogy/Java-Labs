package com.example.lab4.streets;

import com.example.lab4.dto.StreetDto;

import java.util.List;


public interface StreetService {
    StreetDto saveStreet(StreetDto streetDto);
    List<StreetDto> fetchStreetList();
    StreetDto updateStreet (StreetDto streetDto, Long streetId);
    void deleteStreetById(Long streetId);
    List<StreetDto> getAllByName(String name);

}
