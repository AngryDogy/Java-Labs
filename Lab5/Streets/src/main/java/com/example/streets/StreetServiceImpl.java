package com.example.streets;


import com.example.models.DatabaseEntityDoesNotExistException;
import com.example.models.dto.StreetDto;
import com.example.models.entities.Street;
import com.example.models.mapper.StreetMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@ComponentScan("com.example")
public class StreetServiceImpl {
    private final StreetRepository streetRepository;

    private final StreetMapper streetMapper;

    private final RabbitTemplate rabbitTemplate;

    @RabbitListener(queues = "streets-post")
    public void saveStreet(StreetDto streetDto) {
        Street street = convertToEntity(streetDto);
        streetRepository.save(street);
        rabbitTemplate.convertAndSend("streets-exchange","streets.result", streetDto);
    }
   @RabbitListener(queues =  "streets-get")
   public void fetchStreetList() {
        List<Street> streets = streetRepository.findAll();
        rabbitTemplate.convertAndSend("streets-exchange"
                , "streets.result"
                , streets.stream()
                        .map(this::convertToDto)
                        .collect(Collectors.toList()));
    }
    @RabbitListener(queues = "streets-put")
    public void updateStreet(StreetDto streetDto) {
        Street street = convertToEntity(streetDto);
        Street streetDB = streetRepository.findById(streetDto.getId())
                .orElseThrow(() -> new DatabaseEntityDoesNotExistException("Can't find this record in the database"));
        streetDB.setName(street.getName());
        streetDB.setPostcode(street.getPostcode());
        streetRepository.save(streetDB);
        rabbitTemplate.convertAndSend("streets-exchange", "streets.result", streetDto);
    }

    @RabbitListener(queues = "streets-delete")
    public void deleteStreetById(Long studentId) {
        streetRepository.deleteById(studentId);
        String message = "Deleted successfully";
        rabbitTemplate.convertAndSend("streets-exchange", "streets.result", message.getBytes());
    }


    public List<StreetDto> getAllByName(String name) {
        List<Street> streets = streetRepository.getAllByName(name);
        return streets.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    private StreetDto convertToDto(Street street) {
        StreetDto streetDto = streetMapper.mapToDto(street);
        return streetDto;
    }
    private Street convertToEntity(StreetDto streetDto) {

        Street street = streetMapper.mapToEntity(streetDto);
        return street;
    }
    @Autowired
    public StreetServiceImpl(StreetRepository streetRepository, StreetMapper mapper, RabbitTemplate rabbitTemplate) {
        this.streetRepository = streetRepository;
        this.streetMapper = mapper;
        this.rabbitTemplate = rabbitTemplate;
    }
}
