
package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Airport;
import java.sql.*;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Logger;

/**
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class AirportPersistence implements Persistence<Airport> {

    SQLConnection database;
    Function<? super String[], ? extends Airport> creator;
    Function<? super String[], ? extends Airport> creator1;
    Logger logger = Logger.getLogger(AirportPersistence.class.getName());

    public AirportPersistence(SQLConnection database, Function<? super String[], ? extends Airport> creator) {
        this.database = database;
        this.creator = creator;
    }

    public void save(ArrayList<Airport> data) {
        String insertString = "INSERT INTO Airport (ID, airportName) VALUES ";
        for (Airport a : data) {
            insertString += String.format("(%d, %s), ", a.getID(), a.getName());
        }
        insertString += "ON CONFLICT (airportID) DO NOTHING;";

        database.query(insertString);

    }

    public ArrayList<Airport> load() throws SQLException {
        ArrayList<Airport> list = new ArrayList<>();

        try {
            ResultSet airports = database.query("SELECT * from Airport");

            while (airports.next()) {
                list.add(creator.apply(new String[]{airports.getString(1), airports.getString(2)}));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean save(Airport entity) {
        // TODO
        throw new UnsupportedOperationException("Finish method");
    }


    public Airport getAirportFromId(int id) {
        var query = String.format("SELECT * FROM airport WHERE id = %d", id);
        var result = database.query(query);
        try {
            result.next();
            var airportId = (int) result.getObject(1);
            var name = (String) result.getObject(2);
            return new Airport(airportId, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }

    public int getAirportIdByName(String name) {
        try {
            String queryString = String.format("SELECT id FROM airport WHERE airportname = '%s'", name);
            ResultSet result = database.query(queryString);

            if (result.next()) {
                return result.getInt(1);
            } else {
                String warningString = String.format("Airport: '%s' could not be found. Did you enter the name correctly?", name);
//                logger.log( Level.WARNING, ()-> warningString);
                throw new IllegalArgumentException(warningString);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
