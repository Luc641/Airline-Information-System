package com.group_twelve.entities;

public class Option {

    private int ID;
    private String name;
    private int price;

    public Option(int ID, String name, int price) {
        this.ID = ID;
        this.name = name;
        this.price = price;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
