package com.group_twelve.persistence;

import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import java.util.ArrayList;
import java.util.function.Function;
import java.sql.*;
import com.group_twelve.dbconnection.SQLConnection;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class FlightPersistence implements Persistence<Flight>{
    
    SQLConnection database;
    Function<? super String[], ? extends Flight> creator;
    Function<? super String[], ? extends Airport> airportCreator;
    
    public FlightPersistence(SQLConnection database, Function<? super String[], ? extends Flight> creator) {
        this.database = database;
        this.creator = creator;
    }

    // TODO: Refactor save method
//    public void save(ArrayList<Flight> data) {
//        String insertString = "INSERT INTO Flight (ID, flightNr, arrivalTime, departureTime,  flightPrice) VALUES ";
//        for(Flight f : data ) {
//            insertString += String.format("(%d, %d, %s, %s, %d, %d), ", f.getID(), f.getFlightNumber(), f.getArrivalTime().toString(), f.getDepartureTime().toString());
//        }
//        insertString += "ON CONFLICT (flightID) DO NOTHING;";
//
//        database.query(insertString);
//    }
    
    public ArrayList<Flight> load() throws SQLException {
        ArrayList<Flight> list = new ArrayList<>();
        
        try{
            ResultSet flights = database.query("SELECT * from Flight");
            while(flights.next()) {
                list.add(creator.apply(new String[]{flights.getString(1), flights.getString(2), flights.getString(3), flights.getString(4), flights.getString(5), flights.getString(6)}));
            }
        }catch(SQLException e){
            //e.printStackTrace();
        }
        
        return list;
    }
}
