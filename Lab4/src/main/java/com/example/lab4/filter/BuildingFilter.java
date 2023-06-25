package com.example.lab4.filter;

import com.example.lab4.dto.BuildingDto;
import com.example.lab4.dto.FlatDto;
import com.example.lab4.flats.FlatService;
import com.example.lab4.flats.FlatServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BuildingFilter {

    private final FlatFilter flatFilter;
    private final FlatService flatService;

    public List<BuildingDto> filter(String login, List<BuildingDto> buildings) {
        List<BuildingDto> filterResult = new ArrayList<BuildingDto>();
        List<FlatDto>  flats = flatFilter.filter(login, flatService.fetchFlatList());
        for (BuildingDto buildingDto : buildings) {
            boolean check = false;
            for (FlatDto flatDto : flats) {
                if (flatDto.getBuildingDto() != null && flatDto.getBuildingDto().getId() == buildingDto.getId()) {
                    check = true;
                }
            }
            if (check) {
                filterResult.add(buildingDto);
            }
        }
        return filterResult;
    }

    @Autowired
    public BuildingFilter(FlatFilter flatFilter, FlatService flatService) {
        this.flatFilter = flatFilter;
        this.flatService = flatService;
    }

}
