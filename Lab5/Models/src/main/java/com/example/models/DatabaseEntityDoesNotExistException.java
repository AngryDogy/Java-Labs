package com.example.models;

public class DatabaseEntityDoesNotExistException extends RuntimeException{
    public DatabaseEntityDoesNotExistException(String message) {
        super(message);
    }
}
