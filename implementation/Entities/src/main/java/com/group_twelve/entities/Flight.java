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

    private int ID;
    private Plane plane;
    private LocalDateTime arrivalTime;
    private LocalDateTime departureTime;
    private int flightPrice;
    private Airport arrivalAirport;
    private Airport departureAirport;

    public Flight(int ID, Plane pl, LocalDateTime at, LocalDateTime dt, int flightPrice, Airport ap, Airport dp){
        this.ID = ID;
        this.plane = pl;
        this.arrivalTime = at;
        this.departureTime = dt;
        this.flightPrice = flightPrice;
        this.arrivalAirport = ap;
        this.departureAirport = dp;
    }

    // Constructor to return an empty flight. Used when the DB for example cant make / find one but still needs to return it.
    public Flight(int id){
        this.ID = -1;
        this.plane = null;
        this.arrivalTime = null;
        this.departureTime = null;
        this.flightPrice = -1;
        this.arrivalAirport = null;
        this.departureAirport = null;
    }

    public Flight() {

    }

    public int getID() {
        return ID;
    }

    public int getFlightPrice(){
        return flightPrice;
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




    public Plane getPlane() {
        return plane;
    }
}
