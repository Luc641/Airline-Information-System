/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import com.group_twelve.entities.Route;

import java.util.ArrayList;
import java.util.function.Function;
import java.sql.*;

import com.group_twelve.dbconnection.SQLConnection;

/**
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class RoutePersistence implements Persistence<Route> {

    SQLConnection database;
    Function<? super String[], ? extends Route> creator;

    public RoutePersistence(SQLConnection database, Function<? super String[], ? extends Route> creator) {
        this.database = database;
        this.creator = creator;
    }

    public void save(ArrayList<Route> data) {
        String insertString = "INSERT INTO flightRoute (ID, routeName) VALUES ";
        for (Route r : data) {
            insertString += String.format("(%d, %s), ", r.getID()/*, r.getName()*/);
        }
        insertString += "ON CONFLICT (airportID) DO NOTHING;";

        database.query(insertString);
    }

    public ArrayList<Route> load() throws SQLException {
        ArrayList<Route> list = new ArrayList<>();

        try {
            ResultSet routes = database.query("SELECT * from flightroute");

            while (routes.next()) {
                list.add(creator.apply(new String[]{routes.getString(1), routes.getString(2), routes.getString(3)}));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean save(Route entity) {
        // TODO
        throw new UnsupportedOperationException("Finish method");
    }

    /**
     * @param a1 = Arrival airport ID
     * @param a2 = departure airport iD
     * @return
     */
    public ArrayList<Route> getRoutesBasedOnAirports(int a1, int a2) {
        ArrayList<Route> list = new ArrayList<>();

        try {
            String queryString = String.format("SELECT fr.id, fr.routename, fr.flightid FROM flightroute fr " +
                    "inner join flight f on fr.flightid = f.id " +
                    "inner join airport ar on f.arrairportid = ar.id " +
                    "inner join airport ad on f.depairportid = ad.id " +
                    "where depairportid = %s " +
                    "and arrairportid = %d", a2, a1);
            ResultSet routes = database.query(queryString);

            while (routes.next()) {
                list.add(creator.apply(new String[]{routes.getString(1), routes.getString(2), routes.getString(3)}));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

}
