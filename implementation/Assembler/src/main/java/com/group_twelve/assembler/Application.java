/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.assembler;

import com.group_twelve.businesslogic;
import com.group_twelve.persistence.*;
import com.group_twelve.entities.*;
import java.nio.file.Paths;
import java.util.function.Predicate;
/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class Application {
    public static BusinessLogic businessLogic;

    public Application() {
        businessLogic = new BusinessLogic(
                new ManagerAPI(),
                new PersistenceAPI()
        );
        ManagerAPI managerAPI = businessLogic.getManagerAPI();
        managerAPI.addManager(Airport.class, new AirportManager());
        managerAPI.addManager(Route.class, new RouteManager());
        managerAPI.addManager(Flight.class, new FlightManager());
	managerAPI.addManager(Plane.class, new PlaneManager());
        
        PersistenceAPI persistenceAPI = businessLogic.getPersistenceAPI();
        String seperator = ";";
        Predicate<String> lineFilter = (String line) -> Character.isDigit(line.charAt(0));
        persistenceAPI.addPersistence(Airport.class, new AirportPersistence(Paths.get("airport.dat"), seperator, lineFilter, AirportManager::create));
        persistenceAPI.addPersistence(Route.class, new RoutePersistence(Paths.get("route.dat"), seperator, lineFilter, RouteManager::create));
        persistenceAPI.addPersistence(Flight.class, new FlightPersistence(Paths.get("flight.dat"), seperator, lineFilter, FlightManager::create));
	persistenceAPI.addPersistence(Plane.class, new PlanePersistence(Paths.get("plane.dat"), seperator, lineFilter, PlaneManager::create));
    }
    
}
