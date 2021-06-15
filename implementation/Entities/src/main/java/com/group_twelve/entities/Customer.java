package com.group_twelve.entities;

public class Customer {

    private int ID;
    private String name;

    public Customer(int ID, String name) {
        this.ID = ID;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }
}
