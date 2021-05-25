package com.group_twelve.gui;

import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.businesslogic.RouteManager;
import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.entities.Plane;
import com.group_twelve.entities.Route;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class createBookingMain {

    // Define textfield and labels
    @FXML
    private TextField txtDepatureAirport;
    @FXML
    private TableView<Flight> tViewPossibleRoutes;
    @FXML
    public TableColumn<Flight, LocalDateTime> tColDepTime;
    @FXML
    public TableColumn<Flight, LocalDateTime> tColArrTime;
    @FXML
    public TableColumn<Flight, String> tColPrice;
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
    @FXML
    private TableView<Flight> selectedRoutesTableView;
    @FXML
    public TableColumn<String, String> tsColNr;
    @FXML
    public TableColumn<Airport, String> tsColDepAirport;
    @FXML
    public TableColumn<Airport, String> tsColArrAirport;
    @FXML
    public TableColumn<Flight, LocalDateTime> tsColDepartureTime;
    @FXML
    public TableColumn<Flight, LocalDateTime> tsColArrivalTime;
    @FXML
    public TableColumn<Flight, String> tsColPrice;


    /**
     * Initialize the tableviews and their columns
     */
    @FXML
    public void initialize(){

        // Init Select table columns
        tColDepTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        tColArrTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        tColPrice.setCellValueFactory(new PropertyValueFactory<>("flightPrice"));

        // Init selected table columns
        tsColPrice.setCellValueFactory(new PropertyValueFactory<>("nr"));
        tsColDepAirport.setCellValueFactory(new PropertyValueFactory<>("name"));
        tsColArrAirport.setCellValueFactory(new PropertyValueFactory<>("name"));
        tsColDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        tsColArrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        tsColPrice.setCellValueFactory(new PropertyValueFactory<>("flightPrice"));

    }

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
        FlightManager fm = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);

        // Get values from UI
        String depAirport = txtDepatureAirport.getText();
        String arAirport = txtArrivalAirport.getText();

        /**
         * temp override
         */
        depAirport = "Berlin";
        arAirport = "New York";

        // Get flight route(s) that have matching airports
        List<Route> routes = rm.getRouteBasesOnAirports(apm.getAirportId(depAirport), apm.getAirportId(arAirport));

        //TODO: Get all flights attached to the available flight routes.

        /**
         * From here on out stuff is mocked. The above calls work and are implemented.
         * The mocked data assumes that the user entered "berlin" and "New york" as airports.
         */
        ObservableList<Flight> mockFlights = mockFlights();

        tViewPossibleRoutes.setItems(mockFlights);
        selectedRoutesTableView.setItems(mockFlights);

    }


    private ObservableList<Flight> mockFlights(){
        Plane p1 = new Plane(1, 100, 100000);
        Airport berlin = new Airport(1, "Berlin");
        Airport nyc = new Airport(2, "New York");
        LocalDateTime f1da = LocalDateTime.now().plusDays(5); // Arrival
        LocalDateTime fl1dd = LocalDateTime.now().plusDays(4); // Departure
        Flight f1 = new Flight(1, p1, f1da, fl1dd, 10, berlin, nyc);

        Plane p2 = new Plane(2, 50, 5000);
        LocalDateTime f2da = LocalDateTime.now().plusDays(8); // Arrival
        LocalDateTime fl2dd = LocalDateTime.now().plusDays(7); // Departure
        Flight f2 = new Flight(2, p2, f2da, fl2dd, 100, berlin, nyc);

        ObservableList<Flight> allFlights = FXCollections.observableArrayList();

        allFlights.add(f1);
        allFlights.add(f2);

        return allFlights;

    }

}
