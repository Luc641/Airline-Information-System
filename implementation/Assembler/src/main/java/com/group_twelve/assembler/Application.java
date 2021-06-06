/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.assembler;


import com.group_twelve.businesslogic.*;
import com.group_twelve.entities.*;
import com.group_twelve.gui.GUIApp;
import com.group_twelve.persistence.*;
import com.group_twelve.dbconnection.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class Application {
    public static BusinessLogic businessLogic;

    public static void main(String[] args) {
        new Application();
    }
    
    public Application() {
        businessLogic = new BusinessLogic();
        
        Properties prop = new Properties();
        
        try (InputStream input = new FileInputStream("src/main/resources/com/group_twelve/assembler/config.properties")) {
            prop.load(input);

        } catch (IOException ex) {
            //ex.printStackTrace();
        }
        
        SQLConnection database = new SQLConnection();
        database.connect("jdbc:postgresql://127.0.0.1:5432/postgres", "postgres","password", false);

        businessLogic.addManager(Customer.class, new CustomerManager(new CustomerPersistence(database, CustomerManager::create)));
        businessLogic.addManager(Ticket.class, new TicketManager(new TicketPersistence(database, TicketManager::create)));
        businessLogic.addManager(Booking.class, new BookingManager(new BookingPersistence(database, BookingManager::create)));
        businessLogic.addManager(Airport.class, new AirportManager(new AirportPersistence(database, AirportManager::create)));
        businessLogic.addManager(Route.class, new RouteManager(new RoutePersistence(database, RouteManager::create)));
        businessLogic.addManager(Flight.class, new FlightManager(new FlightPersistence(database, FlightManager::create, PlaneManager::createFromID, AirportManager::createFromId, RouteManager::createFromID)));
//	    businessLogic.addManager(Plane.class, new PlaneManager(new PlanePersistence(database, PlaneManager::create)));
        businessLogic.addManager(PriceReduction.class, new PriceReductionManager(new PriceReductionPersistence(database, PriceReductionManager::create)));
    
        GUIApp.startFrontEnd(businessLogic);
    } 
    
}
