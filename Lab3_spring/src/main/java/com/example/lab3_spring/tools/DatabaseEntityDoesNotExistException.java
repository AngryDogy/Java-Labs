package com.example.lab3_spring.tools;

public class DatabaseEntityDoesNotExistException extends RuntimeException{
    public DatabaseEntityDoesNotExistException(String message) {
        super(message);
    }
}
