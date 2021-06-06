package com.group_twelve.persistence;

import com.group_twelve.dbconnection.SQLConnection;
import com.group_twelve.entities.Booking;
import com.group_twelve.entities.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class TicketPersistence implements Persistence<Ticket>{

    SQLConnection db;
    Function<? super Integer[], ? extends Ticket> creator;

    public TicketPersistence(SQLConnection db, Function<? super Integer[], ? extends Ticket> creator) {
        this.db = db;
        this.creator = creator;
    }

    @Override
    public List<Ticket> load() throws SQLException {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public List<Ticket> getTicketsByFlightId(int ID){
        ArrayList<Ticket> list = new ArrayList<>();
        try{
            String query = String.format("SELECT * FROM ticket WHERE flightid = %s", ID);
            ResultSet tickets = db.query(query);
            while(tickets.next()){
                list.add(creator.apply(new Integer[]{tickets.getInt(1),tickets.getInt(2),tickets.getInt(3),tickets.getInt(4), tickets.getInt(5)}));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean save(Ticket ticket) {
        String query = String.format("INSERT INTO ticket (seatnr, flightid, options, customerid) VALUES (%s,%s,%s,%s)", ticket.getSeatNr(), ticket.getFlightID(), ticket.getOptionID(), ticket.getCustomerID());
        try{
            db.query(query);
            return true;
        }catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
