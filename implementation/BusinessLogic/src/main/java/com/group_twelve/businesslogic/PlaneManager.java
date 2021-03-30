/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Plane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Siem Verrijt (s.verrijt@student.fontys.nl, github: @Siem1258)
 */
public class PlaneManager implements Manager<Plane>{

    private List<Plane> planes;
    
    public PlaneManager() {
        this.planes = new ArrayList<>();
    }
    
    public PlaneManager(List<Plane> planes) {
        this.planes = planes;
    }
    
    public static Plane create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Plane create(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Plane plane) {
        planes.add(plane);
    }
    
    public List<Plane> getAll() {
        return planes;
    }
    
    public void clear() {
        planes.clear();
    }
    
    public void setAll(List<Plane> planes) {
        this.planes = planes;
    }
    
}
