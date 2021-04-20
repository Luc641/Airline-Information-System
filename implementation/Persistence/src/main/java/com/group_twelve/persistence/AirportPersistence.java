/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import com.group_twelve.entities.Airport;
import java.util.ArrayList;
import java.util.function.Function;
import java.sql.*;
import com.group_twelve.dbconnection.SQLConnection;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class AirportPersistence implements Persistence<Airport>{

    SQLConnection database;
    Function<? super String[], ? extends Airport> creator;
    
    public AirportPersistence(SQLConnection database, Function<? super String[], ? extends Airport> creator) {
        this.database = database;
        this.creator = creator;
    }
    
    public void save(ArrayList<Airport> data) {
        String insertString = "INSERT INTO Airport (ID, airportName) VALUES ";
        for(Airport a : data ) {
            insertString += String.format("(%d, %s), ", a.getID(), a.getName());
        }
        insertString += "ON CONFLICT (airportID) DO NOTHING;";
        
        database.query(insertString);
        
    }
    
    public ArrayList<Airport> load() throws SQLException {
        ArrayList<Airport> list = new ArrayList<>();
        
        try{
            ResultSet airports = database.query("SELECT * from Airport");
        
            while(airports.next()) {
                list.add(creator.apply(new String[]{airports.getString(1), airports.getString(2)}));
            }
        }catch(SQLException e){
            //e.printStackTrace();
        }
        
        return list;
    }
}
