package com.example.lab3_spring.buildings;

import com.example.lab3_spring.dto.BuildingDto;
import com.example.lab3_spring.hibernate.entities.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingController {
    private final BuildingService buildingService;

    @PostMapping("/buildings")
    public BuildingDto saveBuilding(@Validated @RequestBody BuildingDto buildingDto) {
        return buildingService.saveBuilding(buildingDto);
    }

    @GetMapping("/buildings")
    public List<BuildingDto> fetchStreetList() {
        return buildingService.fetchStreetList();
    }

    @GetMapping("/buildings/{street}")
    public List<BuildingDto> getAllByStreetId(@PathVariable("street") Long streetId) {
        return buildingService.getAllByStreetId(streetId);
    }

    @PutMapping("/buildings/{id}")
    public BuildingDto updateStreet(@RequestBody BuildingDto buildingDto, @PathVariable("id") Long buildingId) {
        return buildingService.updateBuilding(buildingDto, buildingId);
    }

    @DeleteMapping("/buildings/{id}")
    public String deleteStreetById(@PathVariable("id") Long buildingId) {
        buildingService.deleteBuildingById(buildingId);
        return "Deleted Successfully";
    }

    @Autowired
    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }
}
