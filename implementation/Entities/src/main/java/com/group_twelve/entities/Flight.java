/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

import java.time.LocalDateTime;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class Flight {

//    private int ID;
//    private int planeID;
//    private LocalDateTime arrivalTime;
//    private LocalDateTime departureTime;
//    private int flightPrice;
//    private int arrivalAirportID;
//    private int departureAirportID;
//
//    public Flight(int ID, int planeID, LocalDateTime at, LocalDateTime dt, int flightPrice, int arrivalAirportID, int departureAirportID) {
//        this.ID = ID;
//        this.planeID = planeID;
//        this.arrivalTime = at;
//        this.departureTime = dt;
//        this.flightPrice = flightPrice;
//        this.arrivalAirportID = arrivalAirportID;
//        this.departureAirportID = departureAirportID;
//    }

    private final int ID;
    private final Plane plane;
    private LocalDateTime arrivalTime;
    private final LocalDateTime departureTime;
    private final int flightPrice;
    private Airport arrivalAirport;
    private final Airport departureAirport;

    public Flight(int ID, Plane pl, LocalDateTime at, LocalDateTime dt, int flightPrice, Airport ap, Airport dp){
        this.ID = ID;
        this.plane = pl;
        this.arrivalTime = at;
        this.departureTime = dt;
        this.flightPrice = flightPrice;
        this.arrivalAirport = ap;
        this.departureAirport = dp;
    }


    public int getID() {
        return ID;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public Airport getArrivalAirport() {
        return arrivalAirport;
    }

    public Airport getDepartureAirport() {
        return departureAirport;
    }

    public void arrival(LocalDateTime time, Airport location) {
        if(arrivalTime == null && arrivalAirport == null) {
            arrivalTime = time;
            arrivalAirport = location;
        }
    }

    @Override
    public String toString() {
        return "Flight{" +
                "ID=" + ID +
                ", plane=" + plane +
                ", arrivalTime=" + arrivalTime +
                ", departureTime=" + departureTime +
                ", flightPrice=" + flightPrice +
                ", arrivalAirport=" + arrivalAirport +
                ", departureAirport=" + departureAirport +
                '}';
    }


     
}
