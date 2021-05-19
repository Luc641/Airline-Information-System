/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.persistence.FlightPersistence;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class FlightManager implements Manager<Flight>{

    FlightPersistence persistence;

    public FlightManager(FlightPersistence per) {
        this.persistence = per;
    }
    
    public static Flight create(Object[] args) {
        //TODO: Convert some args to the desired entities.
//        return new Flight(Integer.valueOf(args[0]), args[1], args[2], args[3], Integer.valueOf(args[4]), args[5], args[6]);
        return null;
    }

    public List<Flight> getAll(){
        try{
            // Get from DB
            ArrayList<Flight> returnedFlights = persistence.load();

            // Create needed entities
            // TODO: Create entities.
//            Airport test = new Airport();

        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void save() {
        
    }

}
