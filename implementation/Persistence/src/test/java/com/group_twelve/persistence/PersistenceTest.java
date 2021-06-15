/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.persistence;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import static org.assertj.core.api.Assertions.*;
import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.function.Predicate;

/**
 *
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern)
 */
public class PersistenceTest {
    
    public PersistenceTest() {
    }
    
//    @Test
//    public void testSaveLoadAirport() throws IOException {
//        Airport ap1 = new Airport();
//        Airport ap2 = new Airport();
//        ArrayList<Airport> airports = new ArrayList<>() {{
//            add(ap1);
//            add(ap2);
//        }};
//        String seperator = ";";
//        Predicate<String> lineFilter = (String line) -> Character.isDigit(line.charAt(0));
//        AirportPersistence persistence = new AirportPersistence(Paths.get("testAirport.dat"), seperator, lineFilter, businesslogic.AirportManager::create);
//        persistence.save(airports);
//        ArrayList<Airport> loadedAirports = persistence.load();
//        assertThat(loadedAirports).containsExactly(airports.toArray(new Airport[0]));
//    }
//    
//    @Test
//    public void testSaveLoadFlight() throws IOException {
//        Flight fl1 = new Flight();
//        Flight fl2 = new Flight();
//        ArrayList<Flight> flights = new ArrayList<>() {{
//            add(fl1);
//            add(fl2);
//        }};
//        String seperator = ";";
//        Predicate<String> lineFilter = (String line) -> Character.isDigit(line.charAt(0));
//        FlightPersistence persistence = new FlightPersistence(Paths.get("testFlight.dat"), seperator, lineFilter, businesslogic.FlightManager::create);
//        persistence.save(flights);
//        ArrayList<Flight> loadedFlights= persistence.load();
//        assertThat(loadedFlights).containsExactly(flights.toArray(new Flight[0]));
//    }
//    
//    @Test
//    public void testSaveLoadRoute() throws IOException {
//        Route ro1 = new Route(145.95, new ArrayList<Flight>());
//        Route ro2 = new Route(399.99, new ArrayList<Flight>());
//        ArrayList<Route> routes = new ArrayList<>() {{
//            add(ro1);
//            add(ro2);
//        }};
//        String seperator = ";";
//        Predicate<String> lineFilter = (String line) -> Character.isDigit(line.charAt(0));
//        RoutePersistence persistence = new RoutePersistence(Paths.get("testRoute.dat"), seperator, lineFilter, businesslogic.RouteManager::create);
//        persistence.save(routes);
//        ArrayList<Route> loadedRoutes = persistence.load();
//        assertThat(loadedRoutes).containsExactly(routes.toArray(new Route[0]));
//    }

}
