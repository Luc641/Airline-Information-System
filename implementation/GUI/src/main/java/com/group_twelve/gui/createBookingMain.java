package com.group_twelve.gui;

import com.group_twelve.businesslogic.AirportManager;
import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.businesslogic.RouteManager;
import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.entities.Plane;
import com.group_twelve.entities.Route;
import com.group_twelve.entities.selectedRoutes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
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
    private TableView<selectedRoutes> selectedRoutesTableView;
    @FXML
    public TableColumn<selectedRoutes, String> tsColDepAirport;
    @FXML
    public TableColumn<selectedRoutes, String> tsColArrAirport;
    @FXML
    public TableColumn<selectedRoutes, LocalDateTime> tsColDepartureTime;
    @FXML
    public TableColumn<selectedRoutes, LocalDateTime> tsColArrivalTime;
    @FXML
    public TableColumn<selectedRoutes, String> tsColPrice;

    private ObservableList<Flight> foundRoutes = FXCollections.observableArrayList();
    private ObservableList<selectedRoutes> selectedRoutes = FXCollections.observableArrayList();
    /**
     * Initialize the tableviews and their columns
     */
    @FXML
    public void initialize(){

        // Enable the double-mouseclick functionality for the tables.
        tViewPossibleRoutes.setRowFactory(tv -> {
            TableRow<Flight> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (! row.isEmpty())){
                    // Send data to the select flight method.
                    selectRoute(row.getItem());
                }
            });
            return row;
        });

        selectedRoutesTableView.setRowFactory(tv -> {
            TableRow<selectedRoutes> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (!row.isEmpty())){
                    removeSelectedRoute(row.getItem());
                }
            });
            return row;
        });

        // Init the columns of the 2 tables.
        tColDepTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        tColArrTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        tColPrice.setCellValueFactory(new PropertyValueFactory<>("flightPrice"));

        tsColDepAirport.setCellValueFactory(new PropertyValueFactory<>("departureAirportName"));
        tsColArrAirport.setCellValueFactory(new PropertyValueFactory<>("arrivalAirportName"));
        tsColDepartureTime.setCellValueFactory(new PropertyValueFactory<>("departureDateTime"));
        tsColArrivalTime.setCellValueFactory(new PropertyValueFactory<>("arrivalDateTime"));
        tsColPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    /**
     * Accepts the entered text and searches for an existing route. If found, displays the found flights into the
     * tableview.
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
        foundRoutes.addAll(mockFlights());

        tViewPossibleRoutes.setItems(foundRoutes);

    }

    /**
     * Remove the double-clicked flight from the "possible flights" table into the "selected flights" table.
     * Furthermore, the selected flight will be added into a hashmap.
     * @param f = the selected flight
     */
    @FXML
    public void selectRoute(Flight f){

        // TODO: Check if the arrival-and departure times overlap or not. if not, show an error!

        // Check if the flight hasn't been inserted into the hashmap already.
        Long matchingFlights = selectedRoutes.stream()
                .map(v -> v.getFlightID())
                .filter(v -> v == f.getID())
                .count();

        // if equal to 0, then no matching flights are already in the arraylist.
        if(matchingFlights == 0){
            // Remove the flight from the found-flights tableview
            for (int i = 0; i < foundRoutes.size(); i++) {
                if(foundRoutes.get(i).getID() == f.getID()){
                    foundRoutes.remove(i);
                    break;
                }
            }

            // Create a new selectedRoute entity.
            selectedRoutes sl = new selectedRoutes(f.getID(), f.getPlane(), f.getArrivalAirport(), f.getDepartureAirport(), f.getDepartureTime(), f.getArrivalTime(), f.getFlightPrice());

            // Add to the selected routes tableview
            selectedRoutes.add(sl);
            selectedRoutesTableView.setItems(selectedRoutes);
        }else{
            System.out.println("Flight already selected");
        }

    }

    /**
     * This method removes a route from the selected routes table, removes it from the selected routes observable list
     * and puts it back into the foundRoutes arraylist.
     * @param r
     */
    public void removeSelectedRoute(selectedRoutes r){

        // Remove flight from the selectedRoutes list
        for (int i = 0; i < selectedRoutes.size(); i++) {

        }

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
        allFlights.add(f1);

        return allFlights;

    }

}
