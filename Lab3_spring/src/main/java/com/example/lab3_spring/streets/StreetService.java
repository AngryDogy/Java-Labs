package com.example.lab3_spring.streets;

import com.example.lab3_spring.dto.StreetDto;
import com.example.lab3_spring.hibernate.entities.Street;

import java.util.List;


public interface StreetService {
    StreetDto saveStreet(StreetDto streetDto);
    List<StreetDto> fetchStreetList();
    StreetDto updateStreet (StreetDto streetDto, Long streetId);
    void deleteStreetById(Long streetId);
    List<StreetDto> getAllByName(String name);

}
