package com.group_twelve.gui;

import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.businesslogic.BusinessLogic;
import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.persistence.AirportPersistence;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

public class createBookingMain {

    // Define textfield and labels
    @FXML
    private TextField txtDepatureAirport;
    @FXML
    private ListView listAvailableFlights;
    @FXML
    private TextField txtArrivalAirport;
    @FXML
    private Button btnSearchFlights;
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
     * @param a1 = name of airport 1
     * @param a2 = name of airport 2
     */
    @FXML
    public void searchForRoutes(String a1, String a2)  {





    }

}
