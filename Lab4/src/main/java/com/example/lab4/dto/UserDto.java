package com.example.lab4.dto;

import com.example.lab4.hibernate.entities.Role;
import lombok.Data;

@Data
public class UserDto {

    private long id;


    private String login;


    private String password;

    private Role role;
}
