package com.group_twelve.businesslogic;

import com.group_twelve.entities.Booking;
import com.group_twelve.persistence.BookingPersistence;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BookingManager implements Manager<Booking> {

    static BookingPersistence persistence;

    public BookingManager(BookingPersistence bp) {
        persistence = bp;
    }

    public static Booking create(Object[] args){
        var id = Integer.parseInt((String) args[0]);
        LocalDate bookingDate = ((Timestamp) args[1]).toLocalDateTime().toLocalDate();
        var flightRouteID = Integer.parseInt((String) args[2]);
        var employeeID = Integer.parseInt((String) args[3]);
        var priceReductionID = Integer.parseInt((String) args[4]);
        return new Booking(id,bookingDate,flightRouteID,employeeID,priceReductionID);
    }

    public List<Booking> getAll(){
        try{
            return persistence.load();
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public boolean saveBooking(Booking b){
        try{
            return persistence.saveBooking(b);
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
