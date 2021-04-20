/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class Route {
    private int ID;
    private double price;
    private final List<Flight> flights;

    public Route(int ID, double price, ArrayList<Flight> flights) {
        this.ID = ID;
        this.price = price;
        this.flights = flights;
    }

    public int getID() {
        return ID;
    }
    
    public double getPrice() {
        return price;
    }

    public List<Flight> getFlights() {
        return flights;
    }
    
    
    
}
