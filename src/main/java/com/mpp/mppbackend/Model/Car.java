package com.mpp.mppbackend.Model;

public class Car {
    private static int nextId = 1;
    private final int id;
    private String name;
    private String type;
    private String description;

    public Car(String name, String type, String description) {
        this.id = nextId++;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
