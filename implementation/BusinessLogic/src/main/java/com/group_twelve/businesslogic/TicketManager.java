package com.group_twelve.businesslogic;

import com.group_twelve.entities.Booking;
import com.group_twelve.entities.Ticket;
import com.group_twelve.persistence.Persistence;
import com.group_twelve.persistence.TicketPersistence;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements Manager<Ticket> {

    TicketPersistence persistence;

    public TicketManager(TicketPersistence persistence) {
        this.persistence = persistence;
    }

    public static Ticket create(Integer[] args){
        return new Ticket(args[0],args[1],args[2],args[3]);
    }

    @Override
    public List<Ticket> getAll() {
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public boolean save(Ticket object) {
       try{
           return persistence.save(object);
       }catch(Exception e){
           e.printStackTrace();
       }
       return false;
    }

    public List<Ticket> getTicketsByFlightId(int ID){
        try{
            return persistence.getTicketsByFlightId(ID);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        return null;
    }

}
