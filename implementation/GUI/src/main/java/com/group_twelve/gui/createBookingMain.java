package com.group_twelve.gui;

import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.businesslogic.RouteManager;
import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class createBookingMain {

    // Define textfield and labels
    @FXML
    private TextField txtDepatureAirport;
    @FXML
    private ListView listAvailableRoutes;
    @FXML
    private TextField txtArrivalAirport;
    @FXML
    private Button btnSearchFlightRoutes;
    @FXML
    private ListView clistSelectedFlights;
    @FXML
    private DatePicker dpDepartureDate;
    @FXML
    private Button btnSaveBooking;
    @FXML
    private Button btnCancel;

    ObservableList observableList = FXCollections.observableArrayList();

    /**
     *
     * Searches for routes with airport 1 as departure and a2 as arrival.
     * When found, the route will be displayed in the list: listAvailableFlights
     *
     */
    @FXML
    public void searchFlightRoutes(){
        // Init managers
        AirportManager apm = (AirportManager) GUIApp.getBusinessLogicAPI().getManager(Airport.class);
        RouteManager rm = (RouteManager) GUIApp.getBusinessLogicAPI().getManager(Route.class);

        // Get values from UI
        String depAirport = txtDepatureAirport.getText();
        String arAirport = txtArrivalAirport.getText();

        // Get flight route(s) that have matching airports
        List<Route> routes = rm.getRouteBasesOnAirports(apm.getAirportId(depAirport), apm.getAirportId(arAirport));

        //TODO: Get all flights attached to the available flight routes.
        
        // Show in listview
        observableList.addAll(routes.stream().map(s -> s.getRouteName()).collect(Collectors.toList()));
        listAvailableRoutes.setItems(observableList);



    }

}
