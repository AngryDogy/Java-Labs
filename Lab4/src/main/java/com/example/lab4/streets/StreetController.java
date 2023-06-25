package com.example.lab4.streets;

import com.example.lab4.dto.StreetDto;
import com.example.lab4.filter.StreetFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class StreetController {
    private final StreetService streetService;
    private final StreetFilter streetFilter;

    @PostMapping("/streets")
    @PreAuthorize("hasAuthority('WRITE')")
    public StreetDto saveStreet(@Validated @RequestBody StreetDto streetDto) {
        return streetService.saveStreet(streetDto);
    }
    @GetMapping("/streets")
    @PreAuthorize("hasAuthority('READ')")
    public List<StreetDto> fetchStreetList() {
        return streetFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                streetService.fetchStreetList());
    }
    @GetMapping("/streets/{name}")
    @PreAuthorize("hasAuthority('READ')")
    public List<StreetDto> getAllByName(@PathVariable("name") String name) {
        return streetFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                streetService.getAllByName(name));
    }
    @PutMapping("/streets/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public StreetDto updateStreet(@RequestBody StreetDto streetDto, @PathVariable("id") Long streetId) {
        return streetService.updateStreet(streetDto, streetId);
    }
    @DeleteMapping("/streets/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String deleteStreetById(@PathVariable("id") Long streetId) {
        streetService.deleteStreetById(streetId);
        return "Deleted Successfully";
    }
    @Autowired
    public StreetController(StreetService streetService, StreetFilter streetFilter) {
        this.streetService = streetService;
        this.streetFilter = streetFilter;
    }
}
