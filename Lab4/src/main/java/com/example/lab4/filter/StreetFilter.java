package com.example.lab4.filter;

import com.example.lab4.buildings.BuildingService;
import com.example.lab4.buildings.BuildingServiceImpl;
import com.example.lab4.dto.BuildingDto;
import com.example.lab4.dto.StreetDto;
import com.example.lab4.streets.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StreetFilter {

    private final BuildingFilter buildingFilter;
    private final BuildingService buildingService;


    public List<StreetDto> filter(String login, List<StreetDto> streets) {

        List<StreetDto> filterResult = new ArrayList<StreetDto>();
        List<BuildingDto> buildings = buildingFilter.filter(login, buildingService.fetchBuildingList());
        for (StreetDto street : streets) {

            boolean check = false;
            for (BuildingDto building : buildings) {
                if (building.getStreetDto() != null && building.getStreetDto().getId() == street.getId()) {
                    check = true;
                }
            }
            if (check) {
                filterResult.add(street);
            }
        }
        return filterResult;
    }

    @Autowired
    public StreetFilter(BuildingFilter buildingFilter, BuildingService buildingService) {
        this.buildingFilter = buildingFilter;
        this.buildingService = buildingService;
    }
}
