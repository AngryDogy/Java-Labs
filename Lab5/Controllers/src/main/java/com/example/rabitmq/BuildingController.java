package com.example.rabitmq;


import com.example.models.dto.BuildingDto;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
public class BuildingController {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;


    @PostMapping("/buildings")
    @PreAuthorize("hasAuthority('WRITE')")
    public BuildingDto saveBuilding(@Validated @RequestBody BuildingDto buildingDto) {
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.post", buildingDto);
        Message resultMessage = rabbitTemplate.receive("buildings-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            try {
                BuildingDto buildingDtoResult = objectMapper.readValue(resultMessage.getBody(),
                        new TypeReference<BuildingDto>(){});
                return buildingDtoResult;
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @GetMapping("/buildings")
    @PreAuthorize("hasAuthority('READ')")
    public List<BuildingDto> fetchBuildingList() {
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.get", "");
        Message resultMessage = rabbitTemplate.receive("buildings-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            try {
                List<BuildingDto> buildingDtoResult = objectMapper.readValue(resultMessage.getBody(),
                        new TypeReference<List<BuildingDto>>(){});
                return buildingDtoResult;
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

   /* @GetMapping("/buildings/{street}")
    //@PreAuthorize("hasAuthority('READ')")
    public List<BuildingDto> getAllByStreetId(@PathVariable("street") Long streetId) {
        return buildingFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                buildingService.getAllByStreetId(streetId));
    }*/

    @PutMapping("/buildings/put")
    @PreAuthorize("hasAuthority('WRITE')")
    public BuildingDto updateBuilding(@RequestBody BuildingDto buildingDto) {
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.put", buildingDto);
        Message resultMessage = rabbitTemplate.receive("buildings-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            try {
                BuildingDto buildingDtoResult = objectMapper.readValue(resultMessage.getBody(),
                        new TypeReference<BuildingDto>(){});
                return buildingDtoResult;
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @DeleteMapping("/buildings/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String deleteBuildingById(@PathVariable("id") Long buildingId) {
        rabbitTemplate.convertAndSend("buildings-exchange", "buildings.delete", buildingId);
        Message resultMessage = rabbitTemplate.receive("buildings-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            return "Deleted Successfully";
        }
    }

    @Autowired
    public BuildingController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }
    /*@Autowired
    public BuildingController(BuildingService buildingService, BuildingFilter buildingFilter) {
        this.buildingService = buildingService;
        this.buildingFilter = buildingFilter;
    }*/
}
