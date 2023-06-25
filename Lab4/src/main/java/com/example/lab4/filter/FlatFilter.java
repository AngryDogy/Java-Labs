package com.example.lab4.filter;

import com.example.lab4.dto.FlatDto;
import com.example.lab4.hibernate.entities.User;
import com.example.lab4.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FlatFilter {

    public List<FlatDto> filter(String login, List<FlatDto> flats) {

        List<FlatDto> filterResult = new ArrayList<FlatDto>();
        for (FlatDto flatDto : flats) {
            if (flatDto.getUserDto() != null && flatDto.getUserDto().getLogin().equals(login)) {
                filterResult.add(flatDto);
            }
        }
        return filterResult;
    }

}
