package com.example.rabitmq.users;

import lombok.Data;

@Data
public class UserDto {

    private long id;


    private String login;


    private String password;

    private Role role;
}
