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

    private Route route;
    private int crewID;
    private String optionalRemarkt;
    private int planeID;

    public Flight(int ID, Plane pl, LocalDateTime at, LocalDateTime dt, int flightPrice, Airport ap, Airport dp, Route r, int cid, String or, int pid){
        this.ID = ID;
        this.plane = pl;
        this.arrivalTime = at;
        this.departureTime = dt;
        this.flightPrice = flightPrice;
        this.arrivalAirport = ap;
        this.departureAirport = dp;

	this.route = r;
	this.crewID = cid;
	this.optionalRemarkt = or;
	this.planeID = pid;
    }

    public void updateFlightDB() {
	//update the flight with all this data into the database
	String query = "UPDATE Flight SET PlaneID = '".PlaneID."', arrivalTime = '".arrivalTime."', departureTime = '".departureTime."', flightPrice = '".flightPrice."', arrAirportID = '".arrivalAirport.getID()."', depAirportID = '".departureAirport.getID()."' WHERE ID = '".ID."'";
	String query2 = "UPDATE FlightRoute SET routeName = '".route.getRouteName()."' WHERE flightID = '".ID."'";

	//execute both queries
	// TODO: Push both the queries to the database.
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

    public Plane getPlane() {
        return plane;
    }

    public int getPlaneID() {
        return planeID;
    }

    public Route getRoute() {
        return route;
    }
	
    public String getCrewID() {
        return plane;
    }

    public String getOptionalRemarkt() {
        return optionalRemarkt;
    }

    public void setFlightPrice(int price){
	this.flightPrice = price;
    }

    public void setArrivalTime(LocalDateTime ldt){
	this.arrivalTime = ldt;
    }
     
    public void setDepartureTime(LocalDateTime ldt){
	this.departureTime = ldt;
    }
    
    public void setArrivalAirport(Airport air){
	this.arrivalAirport = air;
    }

    public void setDepartureAirport(Airport air){
	this.departureAirport = air;
    }

    public void setPlane(Plane p){
	this.plane = p;
    }

    public void setPlaneID(int pid) {
        this.planeID = pid;
    }

    public void setRoute(Route r){
	this.route = r;
    }

    public void setCrewID(int cid){
	this.crewID = cid;
    }

    public void setOptionalRemarkt(String or){
	// Check if optional remarks has been filled in, otherwise enter none.
        if(or.equals("")){
            this.optionalRemarkt = "none";
        } 
	else
	{
	    this.optionalRemarkt = or;
	}
    }
}
