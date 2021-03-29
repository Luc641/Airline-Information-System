/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package businesslogic;

import entities.Airport;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author timom
 */
public class AirportManager implements Manager<Airport>{

    private List<Airport> airports;
    
    public AirportManager() {
        this.airports = new ArrayList<>();
    }
    
    public AirportManager(List<Airport> airports) {
        this.airports = airports;
    }
    
    public static Airport create() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static Airport create(String[] args) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void add(Airport airport) {
        airports.add(airport);
    }
    
    public List<Airport> getAll() {
        return airports;
    }
    
    public void clear() {
        airports.clear();
    }
    
    public void setAll(List<Airport> airports) {
        this.airports = airports;
    }
    
}
