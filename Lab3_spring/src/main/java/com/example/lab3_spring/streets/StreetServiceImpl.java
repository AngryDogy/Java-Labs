package com.example.lab3_spring.streets;

import com.example.lab3_spring.dto.StreetDto;
import com.example.lab3_spring.hibernate.entities.Street;
import com.example.lab3_spring.tools.DatabaseEntityDoesNotExistException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;

    private final ModelMapper modelMapper;

    @Override
    public StreetDto saveStreet(StreetDto streetDto) {
        Street street = convertToEntity(streetDto);
        streetRepository.save(street);
        return streetDto;
    }

    @Override
    public List<StreetDto> fetchStreetList() {
        List<Street> streets = streetRepository.findAll();
        return streets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


    @Override
    public StreetDto updateStreet(StreetDto streetDto, Long streetId) {
        Street street = convertToEntity(streetDto);
        Street streetDB = streetRepository.findById(streetId).get();
        if (streetDB == null) {
            throw new DatabaseEntityDoesNotExistException("Can't find this record in database");
        }
        streetDB.setName(street.getName());
        streetDB.setPostcode(street.getPostcode());
        streetRepository.save(streetDB);
        return streetDto;
    }

    @Override
    public void deleteStreetById(Long studentId) {
        streetRepository.deleteById(studentId);
    }

    @Override
    public List<StreetDto> getAllByName(String name) {
        List<Street> streets = streetRepository.getAllByName(name);
        return streets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private StreetDto convertToDto(Street street) {
        StreetDto streetDto = modelMapper.map(street, StreetDto.class);
        return streetDto;
    }
    private Street convertToEntity(StreetDto streetDto) {

        Street street = modelMapper.map(streetDto, Street.class);
        return street;
    }
    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository) {
        this.streetRepository = streetRepository;
        this.modelMapper = new ModelMapper();
    }
}
