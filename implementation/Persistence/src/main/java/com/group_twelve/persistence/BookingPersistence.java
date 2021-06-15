package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Booking;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Logger;

public class BookingPersistence implements Persistence<Booking>{

    SQLConnection database;
    Function<? super Object[], ? extends Booking> creator;
    Logger logger = Logger.getLogger(BookingPersistence.class.getName());

    public BookingPersistence(SQLConnection database, Function<? super Object[], ? extends Booking> creator) {
        this.database = database;
        this.creator = creator;
    }

    public ArrayList<Booking> load(){
        ArrayList<Booking> list = new ArrayList<>();

        try{
            ResultSet bookings = database.query("SELECT * FROM booking");
            while(bookings.next()){
                list.add(creator.apply(new Object[]{bookings.getString(1), bookings.getObject(2), bookings.getString(3), bookings.getString(4), bookings.getString(5)}));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Booking b) {
        String queryString = String.format("INSERT INTO booking (bookingdate, flightrouteid, employeeid, pricereductionid) VALUES ('%s',%s,%s,%s)", b.getBookingDate(), b.getFlightRouteID(), b.getEmployeeID(), b.getPriceReductionID());
        try{
            database.insertBooking(queryString);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

//    public boolean saveBooking(Booking b){
//        String queryString = String.format("INSERT INTO booking (bookingdate, flightrouteid, employeeid, pricereductionid) VALUES ('%s',%s,%s,%s)", b.getBookingDate(), b.getFlightRouteID(), b.getEmployeeID(), b.getPriceReductionID());
//        try{
//            return database.insertBooking(queryString);
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }

//    public boolean saveBooking(Booking b){
//        String queryString = String.format("INSERT INTO booking (bookingdate, flightrouteid, employeeid, pricereductionid) VALUES ('%s',%s,%s,%s)", b.getBookingDate(), b.getFlightRouteID(), b.getEmployeeID(), b.getPriceReductionID());
//        System.out.println("queryString = " + queryString);
//        try{
//            ResultSet t = database.query(queryString);
//            System.out.println(t);
//            return true;
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//        return false;
//    }

}
