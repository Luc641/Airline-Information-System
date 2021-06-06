package com.group_twelve.entities;

public class Ticket {

    private int ID;
    private int seatNr;
    private int flightID;
    private int optionID;

    public Ticket(int ID, int seatNr, int flightID, int optionID) {
        this.ID = ID;
        this.seatNr = seatNr;
        this.flightID = flightID;
        this.optionID = optionID;
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

    public int getOptionID() {
        return optionID;
    }
}
