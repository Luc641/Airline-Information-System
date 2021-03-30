/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Route;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class RouteManager implements Manager<Route>{
    
    List<Route> routes;
    
    public RouteManager() {
        this.routes = new ArrayList<>();
    }
    
    public RouteManager(List<Route> routes) {
        this.routes = routes;
    }
    
    public static Route create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Route create(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Route route) {
        routes.add(route);
    }
    
    public List<Route> getAll() {
        return routes;
    }
    
    public void clear() {
        routes.clear();
    }
    
    public void setAll(List<Route> routes) {
        this.routes = routes;
    }
    
}
