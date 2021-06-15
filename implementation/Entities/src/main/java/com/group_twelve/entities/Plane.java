/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

/**
 * @author Siem Verrijt (s.verrijt@student.fontys.nl, github: @Siem1258)
 */
public class Plane {
    private int ID;
    private int seats;
    private int maxWeight;
    private String name;

    public Plane(int ID, int seats, int maxWeight, String name) {
        this.ID = ID;
        this.seats = seats;
        this.maxWeight = maxWeight;
        this.name = name;
    }

    public int getID() {
        return ID;
    }

    public int getSeats() {
        return seats;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Plane{" +
                "ID=" + ID +
                ", seats=" + seats +
                ", maxWeight=" + maxWeight +
                ", name='" + name + '\'' +
                '}';
    }
}
