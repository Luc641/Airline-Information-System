/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.entities;

/**
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class Airport {
    private int ID;
    private String name;
//    private String location;

    public Airport(int ID, String name) {
        this.ID = ID;
        this.name = name;
//        this.location = location;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

//    public String getLocation() {
//        return location;
//    }


    @Override
    public String toString() {
        return name;
    }
}
