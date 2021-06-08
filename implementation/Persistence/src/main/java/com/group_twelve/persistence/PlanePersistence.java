/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Plane;

import java.util.ArrayList;
import java.util.function.Function;
import java.sql.*;

import com.group_twelve.dbconnection.SQLConnection;

/**
 * @author Siem Verrijt (s.verrijt@student.fontys.nl, github: @Siem1258)
 */
public class PlanePersistence implements Persistence<Plane> {

    SQLConnection database;
    Function<? super String[], ? extends Plane> creator;
    public Function<? super Integer, ? extends Plane> planeCreator;

    public PlanePersistence(SQLConnection database,
                            Function<? super String[], ? extends Plane> creator,
                            Function<? super Integer, ? extends Plane> planeCreator) {
        this.database = database;
        this.creator = creator;
        this.planeCreator = planeCreator;
    }

    public void save(ArrayList<Plane> data) {
        String insertString = "INSERT INTO Plane (planeID) VALUES ";
        for (Plane p : data) {
            insertString += String.format("(%d), ", p.getID());
        }
        insertString += "ON CONFLICT (planeID) DO NOTHING;";

        database.query(insertString);
    }

    public ArrayList<Plane> load() throws SQLException {
        ArrayList<Plane> list = new ArrayList<>();

        try {
            ResultSet planes = database.query("SELECT * from Plane");

            while (planes.next()) {
                list.add(creator.apply(new String[]{planes.getString(1), planes.getString(2), planes.getString(3)}));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean save(Plane entity) {
        // TODO
        throw new UnsupportedOperationException("Finish method");
    }

}
