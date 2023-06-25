package com.example.buildings;


import com.example.models.DatabaseEntityDoesNotExistException;
import com.example.models.dto.BuildingDto;
import com.example.models.entities.Building;
import com.example.models.mapper.BuildingMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import com.example.models.entities.Street;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan(basePackages = {"com.example.models"})
public class BuildingServiceImpl {
    private BuildingRepository buildingRepository;
    private  BuildingMapper buildingMapper;
    private  RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "buildings-post")
    public void saveBuilding(BuildingDto buildingDto) {
        Building building = convertToEntity(buildingDto);
        buildingRepository.save(building);
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.result", buildingDto);

    }

    @RabbitListener(queues = "buildings-get")
    public void fetchBuildingList() {
        List<Building> buildings = buildingRepository.findAll();
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.result", buildings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList()));
    }



    @RabbitListener(queues =  "buildings-put")
    public void updateBuilding(BuildingDto buildingDto) {
        Building building = convertToEntity(buildingDto);
        Building buildingDB = buildingRepository.findById(building.getId())
                .orElseThrow(() -> new DatabaseEntityDoesNotExistException("Can't find this record in the database"));
        buildingDB.setName(building.getName());
        buildingDB.setConstructionDate(building.getConstructionDate());
        buildingDB.setFloorsNumber(building.getFloorsNumber());
        buildingDB.setBuildingType(building.getBuildingType());
        buildingDB.setStreet(building.getStreet());
        buildingRepository.save(buildingDB);
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.result", buildingDto);
    }


    @RabbitListener(queues = "buildings-delete")
    public void deleteBuildingById(Long buildingId) {
        buildingRepository.deleteById(buildingId);
        String message = "Deleted successfully";
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.result", message.getBytes());
    }

/*
    public List<BuildingDto> getAllByStreetId(Long streetId) {
        List<Building> buildings = buildingRepository.getAllByStreetId(streetId);
        return buildings.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }*/
    private BuildingDto convertToDto(Building building) {
        return buildingMapper.mapToDto(building);
    }
    private Building convertToEntity(BuildingDto buildingDto) {
        return buildingMapper.mapToEntity(buildingDto);
    }

    public BuildingServiceImpl(BuildingRepository buildingRepository, BuildingMapper buildingMapper, RabbitTemplate rabbitTemplate) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
        this.rabbitTemplate = rabbitTemplate;

    }
}
