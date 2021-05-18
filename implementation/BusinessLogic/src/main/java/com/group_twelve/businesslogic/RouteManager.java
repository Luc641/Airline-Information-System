/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Route;
import com.group_twelve.persistence.RoutePersistence;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class RouteManager implements Manager<Route>{
    
    RoutePersistence persistence;
    
    public RouteManager(RoutePersistence rp) {
        this.persistence = rp;
    }
    
//    public RouteManager(List<Route> routes) {
//        this.routes = routes;
//    }
    
    public static Route create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Route create(String[] args) {
        return new Route(Integer.valueOf(args[0]), args[1], Integer.valueOf(args[2]));
    }
    
    public void add(Route route) {

    }
    
    public List<Route> getAll() {
        try{
//            List<Route> flights = persistence.load();
            return persistence.load();
        }catch(Exception e){
            System.out.println("Failed to get all flight routes");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    /**
     *
     * @param aID = arrivalAirportID
     * @param dID = departureAirportID
     * @return list of found routes that have the arrival- and departureairport in common.
     */
    public List<Route> getRouteBasesOnAirports(int aID, int dID){
        try{
            return persistence.getRoutesBasedOnAirports(aID, dID);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    
    public void clear() {

    }
    
    public void setAll(List<Route> routes) {
//        this.routes = routes;
    }
    
}
