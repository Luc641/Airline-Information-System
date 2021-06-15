package com.group_twelve.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Booking {

    private int ID;
    private LocalDate bookingDate;
    private int flightRouteID;
    private int employeeID;
    private int priceReductionID;

    public Booking(int ID, LocalDate bookingDate, int flightRouteID, int employeeID, int priceReductionID) {
        this.ID = ID;
        this.bookingDate = bookingDate;
        this.flightRouteID = flightRouteID;
        this.employeeID = employeeID;
        this.priceReductionID = priceReductionID;
    }

    public Booking(LocalDate bookingDate, int flightRouteID, int employeeID, int priceReductionID) {
        this.bookingDate = bookingDate;
        this.flightRouteID = flightRouteID;
        this.employeeID = employeeID;
        this.priceReductionID = priceReductionID;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public int getFlightRouteID() {
        return flightRouteID;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public int getPriceReductionID() {
        return priceReductionID;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "ID=" + ID +
                ", bookingDate=" + bookingDate +
                ", flightRouteID=" + flightRouteID +
                ", employeeID=" + employeeID +
                ", priceReductionID=" + priceReductionID +
                '}';
    }
}
