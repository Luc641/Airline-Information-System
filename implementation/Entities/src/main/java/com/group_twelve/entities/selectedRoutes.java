package com.group_twelve.entities;

import java.time.LocalDateTime;

public class selectedRoutes {

    private int flightID;
    private Plane plane;
    private Airport arrivalAirport;
    private String arrivalAirportName;
    private Airport departureAirport;
    private String departureAirportName;
    private LocalDateTime departureDateTime;
    private LocalDateTime arrivalDateTime;
    private int price;
    private int ticketCount;

    public selectedRoutes(int flightID, Plane plane, Airport arrivalAirport, Airport departureAirport, LocalDateTime departureDateTime, LocalDateTime arrivalDateTime, int price, int ticketCount) {
        this.flightID = flightID;
        this.plane = plane;
        this.arrivalAirport = arrivalAirport;
        this.departureAirport = departureAirport;
        this.departureDateTime = departureDateTime;
        this.arrivalDateTime = arrivalDateTime;
        this.price = price;
        this.ticketCount = ticketCount;

        this.departureAirportName = departureAirport.getName();
        this.arrivalAirportName = arrivalAirport.getName();
    }

    public selectedRoutes(){}

    public int getFlightID() {
        return flightID;
    }

    public Plane getPlane() {
        return plane;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public String getArrivalAirportName() {
        return arrivalAirportName;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public String getDepartureAirportName() {
        return departureAirportName;
    }

    public LocalDateTime getDepartureDateTime() {
        return departureDateTime;
    }

    public LocalDateTime getArrivalDateTime() {
        return arrivalDateTime;
    }

    public int getPrice() {
        return price;
    }

}
