/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Plane;
import com.group_twelve.persistence.PlanePersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Siem Verrijt (s.verrijt@student.fontys.nl, github: @Siem1258)
 */
public class PlaneManager implements Manager<Plane> {

    static PlanePersistence persistence;

    private List<Plane> planes;

    public PlaneManager(PlanePersistence per) {
        persistence = per;

    }

    public PlaneManager(List<Plane> planes) {
        this.planes = planes;
    }

    public static Plane create(Object[] args) {
        var id = Integer.parseInt((String) args[0]);
        var seats = Integer.parseInt((String) args[1]);
        var maxWeight = Integer.parseInt((String) args[2]);
        var name = (String) args[3];

        return new Plane(id, seats, maxWeight, name);

    }

    public static Plane create(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static Plane createFromID(Integer ID) {
        return null;
    }

    public void add(Plane plane) {
        planes.add(plane);
    }

    public List<Plane> getAll() {
        return planes;
    }

    @Override
    public boolean save(Plane object) {
        return false;
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        return null;
    }

    public void clear() {
        planes.clear();
    }

    public void setAll(List<Plane> planes) {
        this.planes = planes;
    }

}
