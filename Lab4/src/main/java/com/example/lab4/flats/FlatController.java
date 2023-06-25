package com.example.lab4.flats;

import com.example.lab4.dto.FlatDto;
import com.example.lab4.filter.FlatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class FlatController {
    private final FlatService flatService;
    private final FlatFilter flatFilter;

    @PostMapping("/flats")
    @PreAuthorize("hasAuthority('WRITE')")
    public FlatDto saveStreet(@Validated @RequestBody FlatDto flatDto) {
        return flatService.saveFlat(flatDto);
    }
    @GetMapping("/flats")
    @PreAuthorize("hasAuthority('READ')")
    public List<FlatDto> fetchFlatList() {
        List<FlatDto> flats = flatService.fetchFlatList();
        return flatFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(), flats);

    }
    @GetMapping("/flats/{building}")
    @PreAuthorize("hasAuthority('READ')")
    public List<FlatDto> getAllByBuildingId(@PathVariable("building") Long buildingId) {
        return flatFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                flatService.getAllByBuildingId(buildingId));
    }
    @PutMapping("/flats/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public FlatDto updateStreet(@RequestBody FlatDto flatDto, @PathVariable("id") Long flatId) {
        return flatService.updateFlat(flatDto, flatId);
    }
    @DeleteMapping("/flats/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String deleteFlatsById(@PathVariable("id") Long flatId) {
        flatService.deleteFlatById(flatId);
        return "Deleted Successfully";
    }

    @Autowired
    public FlatController(FlatService flatService, FlatFilter filter) {
        this.flatService = flatService;
        this.flatFilter = filter;
    }
}
