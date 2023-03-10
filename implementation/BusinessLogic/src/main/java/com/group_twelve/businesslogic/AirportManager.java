/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.businesslogic;

import com.group_twelve.entities.Airport;
import com.group_twelve.persistence.AirportPersistence;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class AirportManager implements Manager<Airport> {

    static AirportPersistence airportPersistence;

    public AirportManager(AirportPersistence ap) {
        airportPersistence = ap;

    }

    public AirportManager(){}

    public static Airport create(String[] args) {
        return new Airport(Integer.parseInt(args[0]), args[1]);
    }

    public static Airport createFromId(Integer id) {
        return airportPersistence.getAirportFromId(id);
    }

    public void add(Airport airport) {
    }

    public List<Airport> getAll() {
        try {
            return airportPersistence.load();
        } catch (Exception e) {
            System.out.println("Failed to get all airports");
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public boolean save(Airport object) {
        return false;
    }

    @Override
    public ArrayList<String> validateInput(String a1, String a2, String tCount) {
        return null;
    }

    public Airport get(Integer id) {
        return airportPersistence.getAirportFromId(id);
    }

    public int getAirportId(String name) {
        try {
            return airportPersistence.getAirportIdByName(name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void clear() {
    }

    public void setAll(List<Airport> airports) {
    }

    public void init() {
        System.out.println("hallo");
    }
}
