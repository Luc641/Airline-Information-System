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
    private int flightNumber;
    private LocalDateTime arrivalTime, departureTime;
    private Airport arrivalAirport, departureAirport;
    private Route route;

    public Flight(int flightNumber , LocalDateTime departureTime, Airport departureAirport, Route route) {
        this.flightNumber = flightNumber;
        this.arrivalTime = null;
        this.departureTime = departureTime;
        this.arrivalAirport = null;
        this.departureAirport = departureAirport;
        this.route = route;
    }

    public int getFlightNumber() {
        return flightNumber;
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

    public Route getRoute() {
        return route;
    }
    
    public void arrival(LocalDateTime time, Airport location) {
        if(arrivalTime == null && arrivalAirport == null) {
            arrivalTime = time;
            arrivalAirport = location;
        }
    }
    
    
}
