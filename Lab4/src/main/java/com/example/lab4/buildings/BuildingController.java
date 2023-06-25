package com.example.lab4.buildings;

import com.example.lab4.dto.BuildingDto;
import com.example.lab4.filter.BuildingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BuildingController {
    private final BuildingService buildingService;
    private final BuildingFilter buildingFilter;

    @PostMapping("/buildings")
    @PreAuthorize("hasAuthority('WRITE')")
    public BuildingDto saveBuilding(@Validated @RequestBody BuildingDto buildingDto) {
        return buildingService.saveBuilding(buildingDto);
    }

    @GetMapping("/buildings")
    @PreAuthorize("hasAuthority('READ')")
    public List<BuildingDto> fetchBuildingList() {
        return buildingFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                buildingService.fetchBuildingList());
    }

    @GetMapping("/buildings/{street}")
    @PreAuthorize("hasAuthority('READ')")
    public List<BuildingDto> getAllByStreetId(@PathVariable("street") Long streetId) {
        return buildingFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                buildingService.getAllByStreetId(streetId));
    }

    @PutMapping("/buildings/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public BuildingDto updateBuilding(@RequestBody BuildingDto buildingDto, @PathVariable("id") Long buildingId) {
        return buildingService.updateBuilding(buildingDto, buildingId);
    }

    @DeleteMapping("/buildings/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public void deleteBuildingById(@PathVariable("id") Long buildingId) {
        buildingService.deleteBuildingById(buildingId);
    }

    @Autowired
    public BuildingController(BuildingService buildingService, BuildingFilter buildingFilter) {
        this.buildingService = buildingService;
        this.buildingFilter = buildingFilter;
    }
}
