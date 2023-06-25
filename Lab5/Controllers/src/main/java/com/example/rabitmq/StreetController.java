package com.example.rabitmq;


import com.example.models.dto.StreetDto;
import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;
import java.util.List;

@RestController
public class StreetController {
    //private final StreetService streetService;
    //private final StreetFilter streetFilter;
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @PostMapping("/streets")
    @PreAuthorize("hasAuthority('WRITE')")
    public StreetDto saveStreet(@Validated @RequestBody StreetDto streetDto) {
        rabbitTemplate.convertAndSend("streets-exchange", "streets.post", streetDto);
        Message resultMessage = rabbitTemplate.receive("streets-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            try {
                StreetDto streetDtoResult = objectMapper.readValue(resultMessage.getBody(),
                        new TypeReference<StreetDto>(){});
                return streetDtoResult;
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @GetMapping("/streets")
    @PreAuthorize("hasAuthority('READ')")
    public List<StreetDto> fetchStreetList() {
        rabbitTemplate.convertAndSend("streets-exchange", "streets.get", "");
        Message resultMessage = rabbitTemplate.receive("streets-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            try {
                List<StreetDto> streetsDtoResult = objectMapper.readValue(resultMessage.getBody(),
                        new TypeReference<List<StreetDto>>() {});
                return streetsDtoResult;
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    //@GetMapping("/streets/{name}")
    //@PreAuthorize("hasAuthority('READ')")
    /*public List<StreetDto> getAllByName(@PathVariable("name") String name) {
        rabbitTemplate.convertAndSend("");
        return streetFilter.filter(SecurityContextHolder.getContext().getAuthentication().getName(),
                streetService.getAllByName(name));
    }*/
    @PutMapping("/streets/put")
    @PreAuthorize("hasAuthority('WRITE')")
    public StreetDto updateStreet(@Validated @RequestBody StreetDto streetDto) {
        rabbitTemplate.convertAndSend("streets-exchange", "streets.put", streetDto);
        Message resultMessage = rabbitTemplate.receive("streets-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            try {
                StreetDto streetDtoResult = objectMapper.readValue(resultMessage.getBody(),
                        new TypeReference<StreetDto>() {});
                return streetDtoResult;
            } catch (StreamReadException e) {
                throw new RuntimeException(e);
            } catch (DatabindException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    @DeleteMapping("/streets/{id}")
    @PreAuthorize("hasAuthority('WRITE')")
    public String deleteStreetById(@PathVariable("id") Long streetId) {
        rabbitTemplate.convertAndSend("streets-exchange", "streets.delete", streetId);
        Message resultMessage = rabbitTemplate.receive("streets-result", 5000);
        if (resultMessage == null)  {
            throw new RuntimeException("Failed to receive result message");
        }
        else {
            return "Deleted Successfully";
        }
    }

    @Autowired
    public StreetController(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }
   /* @Autowired
    public StreetController(StreetService streetService, StreetFilter streetFilter) {
        this.streetService = streetService;
        this.streetFilter = streetFilter;
    }*/
}
