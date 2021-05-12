package com.group_twelve.businesslogic;

import com.group_twelve.entities.Airport;
import com.group_twelve.persistence.AirportPersistence;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */

public class AirportManager implements Manager<Airport>{

    private final AirportPersistence persistence;
    
    public AirportManager(AirportPersistence persistence) {
        this.persistence = persistence;
    }
    
    public static Airport createAirportFromArgs(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Airport airport) {
        //validate
        //persistence.add(airport)
    }
    
}
