/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

/**
 *
 * @author Siem Verrijt (s.verrijt@student.fontys.nl, github: @Siem1258)
 */
public class Plane{
    private int seats;
    private int maxWeight;

    public Plane(int seats, int maxWeight) {
        this.seats = seats;
        this.maxWeight = maxWeight;
    }

    public String getSeats() {
        return seats;
    }

    public String getMaxWeight() {
        return maxWeight;
    } 
    
}
