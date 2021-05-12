package com.group_twelve.gui;

import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.entities.Airport;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;


public class createBookingMain {

    // Define textfield and labels
    @FXML
    private TextField txtDepatureAirport;
    @FXML
    private ListView listAvailableFlights;
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


    /**
     *
     * Searches for routes with airport 1 as departure and a2 as arrival.
     * When found, the route will be displayed in the list: listAvailableFlights
     *
     */
    @FXML
    public void searchFlightRoutes(){

        AirportManager apm = (AirportManager) GUIApp.getBusinessLogicAPI().getManager(AirportManager.class);

        ArrayList<Airport> list = (ArrayList<Airport>) apm.getAll();

        System.out.println("ll");
    }

}
