/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class Route {
    private int ID;
    private String routeName;
    private int flightID;

    public Route(int ID, String routeName, int flightID) {
        this.ID = ID;
        this.routeName = routeName;
        this.flightID = flightID;
    }

    public int getID() {
        return ID;
    }

}
