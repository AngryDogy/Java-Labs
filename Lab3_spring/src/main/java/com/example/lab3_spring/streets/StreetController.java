package com.example.lab3_spring.streets;

import com.example.lab3_spring.dto.StreetDto;
import com.example.lab3_spring.hibernate.entities.Street;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StreetController {
    private final StreetService streetService;

    @PostMapping("/streets")
    public StreetDto saveStreet(@Validated @RequestBody StreetDto streetDto) {
        return streetService.saveStreet(streetDto);
    }
    @GetMapping("/streets")
    public List<StreetDto> fetchStreetList() {
        return streetService.fetchStreetList();
    }
    @GetMapping("/streets/{name}")
    public List<StreetDto> getAllByName(@PathVariable("name") String name) {
        return streetService.getAllByName(name);
    }
    @PutMapping("/streets/{id}")
    public StreetDto updateStreet(@RequestBody StreetDto streetDto, @PathVariable("id") Long streetId) {
        return streetService.updateStreet(streetDto, streetId);
    }
    @DeleteMapping("/streets/{id}")
    public String deleteStreetById(@PathVariable("id") Long streetId) {
        streetService.deleteStreetById(streetId);
        return "Deleted Successfully";
    }
    @Autowired
    public StreetController(StreetService streetService) {
        this.streetService = streetService;
    }
}
