package com.example.lab4.hibernate.entities;

public enum BuildingType {

    LIVING_SPACE("Living_space"),
    COMMERCIAL_SPACE("Commercial_space"),
    GARAGE("Garage"),
    UTILITY_ROOM("Utility_room");


    private final String buildingType;
    BuildingType(String buildingType) {
        this.buildingType = buildingType;

    }
}
