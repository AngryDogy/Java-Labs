package com.example.lab4.tools;

public class DatabaseEntityDoesNotExistException extends RuntimeException{
    public DatabaseEntityDoesNotExistException(String message) {
        super(message);
    }
}
