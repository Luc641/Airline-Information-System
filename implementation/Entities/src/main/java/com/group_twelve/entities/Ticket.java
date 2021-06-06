package com.group_twelve.entities;

import java.util.List;

public class Ticket {

    private int ID;
    private int seatNr;
    private int flightID;
    private List<Integer> optionID;
    private int customerID;

    public Ticket(int ID, int seatNr, int flightID, List<Integer> optionID, int c) {
        this.ID = ID;
        this.seatNr = seatNr;
        this.flightID = flightID;
        this.optionID = optionID;
        this.customerID = c;
    }

    public int getCustomerID() {
        return customerID;
    }

    public int getID() {
        return ID;
    }

    public int getSeatNr() {
        return seatNr;
    }

    public int getFlightID() {
        return flightID;
    }

    public List<Integer> getOptionID() {
        return optionID;
    }
}
