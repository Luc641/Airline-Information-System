package com.group_twelve.businesslogic;

import com.group_twelve.entities.Booking;
import com.group_twelve.persistence.BookingPersistence;
import com.group_twelve.persistence.Persistence;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BookingManager implements Manager<Booking> {

    Persistence<Booking> persistence;

    public BookingManager(Persistence<Booking> bp) {
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

    public boolean save(Booking booking){
        try{
            // TODO: Refactor correctly.
            persistence.save(booking);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public ArrayList<String> validateInput(String a1, String a2, String tCount){

        // 1: Validate that both a1 and a2 only contain letters and that tCount only contains number(s). Also check that the strings arent empty.
        if(!a1.matches("[a-zA-Z]+") || !a2.matches("[a-zA-Z]+") || !tCount.matches("\\d+") || a1.equals("") || a2.equals("") || tCount.equals("")){
            return new ArrayList<String>();
        }else{
            // 3: Uppercase the first letter of both a1 and a2 so that it conforms with the style of the DB.
            String ua1 = a1.substring(0, 1).toUpperCase() + a1.substring(1);
            String ua2 = a2.substring(0, 1).toUpperCase() + a2.substring(1);

            // Send back the new data
            ArrayList<String> t = new ArrayList<>();
            t.add(ua1);
            t.add(ua2);
            t.add(tCount);

            System.out.println(tCount.matches("\\d+"));

            return t;
        }
    }

}
