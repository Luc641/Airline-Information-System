/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Airport;
import com.group_twelve.persistence.AirportPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class AirportManager implements Manager<Airport>{

    AirportPersistence airportPersistence;
    
    public AirportManager(AirportPersistence ap) {
        this.airportPersistence = ap;

    }

    public static Airport create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Airport create(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Airport airport) {
    }
    
    public List<Airport> getAll() {
        try{
            return airportPersistence.load();
        }catch(Exception e){
            System.out.println("Failed to get all airports");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public void clear() {
    }
    
    public void setAll(List<Airport> airports) {
    }

    public void init() {
        System.out.println("hallo");
    }
}
