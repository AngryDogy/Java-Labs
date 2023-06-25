package com.example.lab3_spring.flats;

import com.example.lab3_spring.dto.FlatDto;
import com.example.lab3_spring.hibernate.entities.Flat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class FlatController {
    private final FlatService flatService;

    @PostMapping("/flats")
    public FlatDto saveStreet(@Validated @RequestBody FlatDto flatDto) {
        return flatService.saveFlat(flatDto);
    }
    @GetMapping("/flats")
    public List<FlatDto> fetchFlatList() {
        return flatService.fetchFlatList();
    }
    @GetMapping("/flats/{building}")
    public List<FlatDto> getAllByBuildingId(@PathVariable("building") Long buildingId) {
        return flatService.getAllByBuildingId(buildingId);
    }
    @PutMapping("/flats/{id}")
    public FlatDto updateStreet(@RequestBody FlatDto flatDto, @PathVariable("id") Long flatId) {
        return flatService.updateFlat(flatDto, flatId);
    }
    @DeleteMapping("/flats/{id}")
    public String deleteFlatsById(@PathVariable("id") Long flatId) {
        flatService.deleteFlatById(flatId);
        return "Deleted Successfully";
    }

    @Autowired
    public FlatController(FlatService flatService) {
        this.flatService = flatService;
    }
}
