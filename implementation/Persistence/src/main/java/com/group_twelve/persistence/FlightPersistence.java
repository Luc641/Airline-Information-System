package com.group_twelve.persistence;

import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;

import java.util.ArrayList;
import java.util.function.Function;
import java.sql.*;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Plane;
import com.group_twelve.entities.Route;

/**
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class FlightPersistence implements Persistence<Flight> {

    SQLConnection database;
    public Function<? super Object[], ? extends Flight> creator;
    public Function<? super Integer, ? extends Plane> planeCreator;
    public Function<? super Integer, ? extends Airport> airportCreator;
    public Function<? super Integer, ? extends Route> routeCreator;

    public FlightPersistence(SQLConnection database,
                             Function<? super Object[], ? extends Flight> creator,
                             Function<? super Integer, ? extends Plane> planeCreator,
                             Function<? super Integer, ? extends Airport> airportCreator,
                             Function<? super Integer, ? extends Route> routeCreator) {

        this.database = database;
        this.creator = creator;
        this.planeCreator = planeCreator;
        this.airportCreator = airportCreator;
        this.routeCreator = routeCreator;
    }


    public ArrayList<Flight> load() throws SQLException {
        ArrayList<Flight> list = new ArrayList<>();

        try {
            ResultSet flights = database.query("SELECT * from Flight");
            while (flights.next()) {
                list.add(creator.apply(new Object[]{flights.getString(1), flights.getString(2), flights.getObject(3), flights.getObject(4), flights.getString(5), flights.getString(6), flights.getString(7)}));
            }
        } catch (SQLException e) {
            //e.printStackTrace();
        }

        return list;
    }

    @Override
    public boolean save(Flight flight) {
        var query = String.format("INSERT INTO flight (planeid, arrivaltime, departuretime, flightprice, arrairportid, depairportid) VALUES(%s,'%s','%s',%s,%s,%s)", flight.getPlane().getID(), flight.getArrivalTime(), flight.getDepartureTime(), flight.getFlightPrice(), flight.getArrivalAirport(), flight.getDepartureAirport());
        try {
            database.query(query);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Flight getFlightFromId(int ID) {
        var query = String.format("SELECT * FROM flight WHERE id = %d", ID);
        var flights = database.query(query);
        try {
            flights.next();
            return creator.apply(new Object[]{flights.getString(1), flights.getString(2), flights.getObject(3), flights.getObject(4), flights.getString(5), flights.getString(6), flights.getString(7)});
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new Flight(-1);
    }

    public Plane getPlaneFromId(int id) {
        var query = String.format("SELECT id, typename FROM plane WHERE id = %d", id);
        var result = database.query(query);
        try {
            result.next();
            var planeId = (int) result.getObject(1);
            var name = (String) result.getObject(2);
            return new Plane(planeId, 0, 0, name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }


    public void deleteById(int id) {
        database.query(String.format("DELETE FROM flightroute WHERE flightid = %d", id));
        database.query(String.format("DELETE FROM flight WHERE id = %d", id));
    }

    public void editPriceById(int id, int newPrice) {
        database.query(String.format("UPDATE flight SET flightprice = %d WHERE id = %d", newPrice, id));
    }
}
