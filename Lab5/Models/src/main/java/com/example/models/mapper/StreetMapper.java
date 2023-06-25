package com.example.models.mapper;


import com.example.models.dto.StreetDto;
import com.example.models.entities.Street;
import org.springframework.stereotype.Component;

@Component
public class StreetMapper {
    public StreetDto mapToDto(Street street) {
        if (street == null) {
            return null;
        }
        StreetDto streetDto = new StreetDto();
        streetDto.setId(street.getId());
        streetDto.setName(street.getName());
        streetDto.setPostcode(street.getPostcode());
        return streetDto;
    }
    public Street mapToEntity(StreetDto streetDto) {
        if (streetDto == null) {
            return null;
        }
        Street street = new Street();
        street.setId(streetDto.getId());
        street.setName(streetDto.getName());
        street.setPostcode(streetDto.getPostcode());
        return street;
    }

    public StreetMapper() {

    }
}
