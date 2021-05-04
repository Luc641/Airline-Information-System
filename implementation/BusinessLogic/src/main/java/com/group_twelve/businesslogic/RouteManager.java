package com.group_twelve.businesslogic;

import com.group_twelve.entities.Route;
import com.group_twelve.persistence.RoutePersistence;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */

public class RouteManager implements Manager<Route>{

    private final RoutePersistence persistence;
    
    public RouteManager(RoutePersistence persistence) {
        this.persistence = persistence;
    }
    
    public static Route createRouteFromArgs(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Route route) {
        //validate
        //persistence.add(route)
    }
    
}
