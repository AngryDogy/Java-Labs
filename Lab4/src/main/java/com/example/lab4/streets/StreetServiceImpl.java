package com.example.lab4.streets;

import com.example.lab4.dto.StreetDto;
import com.example.lab4.hibernate.entities.Street;
import com.example.lab4.mapper.StreetMapper;
import com.example.lab4.tools.DatabaseEntityDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StreetServiceImpl implements StreetService {
    private final StreetRepository streetRepository;

    private final StreetMapper streetMapper;

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
        Street streetDB = streetRepository.findById(streetId)
                .orElseThrow(() -> new DatabaseEntityDoesNotExistException("Can't find this record in the database"));
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
        StreetDto streetDto = streetMapper.mapToDto(street);
        return streetDto;
    }
    private Street convertToEntity(StreetDto streetDto) {

        Street street = streetMapper.mapToEntity(streetDto);
        return street;
    }
    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository, StreetMapper mapper) {
        this.streetRepository = streetRepository;
        this.streetMapper = mapper;
    }
}
