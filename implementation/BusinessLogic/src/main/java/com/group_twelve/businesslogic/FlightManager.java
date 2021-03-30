/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Flight;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class FlightManager implements Manager<Flight>{
    
    List<Flight> flights;
    
    public FlightManager() {
        this.flights = new ArrayList<>();
    }
    
    public FlightManager(List<Flight> flights) {
        this.flights = flights;
    }
    
    public static Flight create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Flight create(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Flight flight) {
        flights.add(flight);
    }
    
    public List<Flight> getAll() {
        return flights;
    }
    
    public void clear() {
        flights.clear();
    }
    
    public void setAll(List<Flight> flights) {
        this.flights = flights;
    }
    
}
