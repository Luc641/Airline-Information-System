/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.entities.Plane;
import com.group_twelve.entities.selectedRoutes;
import com.group_twelve.persistence.FlightPersistence;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class FlightManager implements Manager<Flight> {

    static FlightPersistence persistence;

    public FlightManager(FlightPersistence per) {
        persistence = per;
    }

    public static Flight create(Object[] args) {
        var id = Integer.parseInt((String) args[0]);
        Plane plane = new Plane(Integer.parseInt((String) args[1]), 0, 0);
        LocalDateTime at = ((Timestamp) args[2]).toLocalDateTime();
        LocalDateTime dt = ((Timestamp) args[3]).toLocalDateTime();
        int flightPrice = Integer.parseInt((String) args[4]);
        Airport ap = persistence.airportCreator.apply(Integer.parseInt((String) args[5]));
        Airport dp = persistence.airportCreator.apply(Integer.parseInt((String) args[6]));

        return new Flight(id, plane, at, dt, flightPrice, ap, dp);
    }

    @Override
    public boolean save(Flight object) {
        return false;
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        return null;
    }

    public List<Flight> getAll() {
        try {
            // Get from DB
            return persistence.load();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public Flight getFlightById(int ID) {
        try {
            return persistence.getFlightFromId(ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Flight(-1);
    }

    public void delete(int id) {
        persistence.deleteById(id);
    }

    public boolean checkForFlightOverlap(selectedRoutes f1, Flight f2) {

        LocalDateTime arrival = f1.getArrivalDateTime();
        LocalDateTime departure = f2.getDepartureTime();

        int overlap = arrival.compareTo(departure);

        return overlap >= 0;

    }

}
