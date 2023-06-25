package com.example.rabitmq.users;


public enum Permission {

    READ("READ"),
    WRITE("WRITE");


    private final String permission;

    Permission(String permission) {
        this.permission = permission;
    }
    public String getPermission() {
        return permission;
    }
}
