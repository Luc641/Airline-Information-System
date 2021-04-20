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
         GUIApp app = new GUIApp();
         app.startFrontEnd(businessLogic);
    }
    
    public Application() {
        businessLogic = new BusinessLogic(
                new ManagerAPI(),
                new PersistenceAPI()
        );
        
        Properties prop = new Properties();
        
        try (InputStream input = new FileInputStream("src/main/resources/com/group_twelve/assembler/config.properties")) {
            prop.load(input);

        } catch (IOException ex) {
            //ex.printStackTrace();
        }
        
        SQLConnection database = new SQLConnection();
        database.connect(prop.getProperty("db.url"), prop.getProperty("db.user"), prop.getProperty("db.password"), false);
        
        ManagerAPI managerAPI = businessLogic.getManagerAPI();
        managerAPI.addManager(Airport.class, new AirportManager());
        managerAPI.addManager(Route.class, new RouteManager());
        managerAPI.addManager(Flight.class, new FlightManager());
	managerAPI.addManager(Plane.class, new PlaneManager());
        
        PersistenceAPI persistenceAPI = businessLogic.getPersistenceAPI();
        persistenceAPI.addPersistence(Airport.class, new AirportPersistence(database, AirportManager::create));
        persistenceAPI.addPersistence(Route.class, new RoutePersistence(database, RouteManager::create));
        persistenceAPI.addPersistence(Flight.class, new FlightPersistence(database, FlightManager::create));
	persistenceAPI.addPersistence(Plane.class, new PlanePersistence(database, PlaneManager::create));
    } 
    
}
