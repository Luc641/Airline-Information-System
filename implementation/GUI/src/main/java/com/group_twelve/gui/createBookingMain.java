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
    @FXML
    private Label lblWarning;

    private ObservableList<Flight> foundRoutes = FXCollections.observableArrayList();
    private ObservableList<selectedRoutes> selectedRoutes = FXCollections.observableArrayList();

    AirportManager apm;
    RouteManager rm;
    FlightManager fm;
    /**
     * Initialize the tableviews and their columns
     */
    @FXML
    public void initialize(){

        // Init managers
        apm = (AirportManager) GUIApp.getBusinessLogicAPI().getManager(Airport.class);
        rm = (RouteManager) GUIApp.getBusinessLogicAPI().getManager(Route.class);
        fm = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);

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
        // Empty list before showing newly found routes.
        foundRoutes = FXCollections.observableArrayList();
        foundRoutes.addAll(mockFlights());
        
        tViewPossibleRoutes.setItems(foundRoutes);

    }

    /**
     * Remove the double-clicked flight from the "possible flights" table into the "selected flights" table.
     * Furthermore, the selected flight will be added into a hashmap.
     * @param f = the selected flight
     */
    @FXML
    public void selectRoute(Flight f) {
        // Reset the warningLabel in case that there is a warning active.
        lblWarning.setText("");

        // Check if the selected flight overlaps with the last inserted selected flight. (others dont have to be
        // checked because those cannot overlap.
        if (selectedRoutes.size() > 0 && fm.checkForFlightOverlap(selectedRoutes.get(selectedRoutes.size() - 1), f)) {
            // If overlap, show error and cancel the selection of the route.
            lblWarning.setText("ERROR: The departure and arrival times overlap!");
        } else {
            // Check if the flight hasn't been inserted into the hashmap already.
            Long matchingFlights = selectedRoutes.stream()
                    .map(v -> v.getFlightID())
                    .filter(v -> v == f.getID())
                    .count();

            // if equal to 0, then no matching flights are already in the arraylist.
            if (matchingFlights == 0) {
                // Remove the flight from the found-flights tableview
                for (int i = 0; i < foundRoutes.size(); i++) {
                    if (foundRoutes.get(i).getID() == f.getID()) {
                        foundRoutes.remove(i);
                        break;
                    }
                }

                // Create a new selectedRoute entity.
                selectedRoutes sl = new selectedRoutes(f.getID(), f.getPlane(), f.getArrivalAirport(), f.getDepartureAirport(), f.getDepartureTime(), f.getArrivalTime(), f.getFlightPrice());

                // Add to the selected routes tableview
                selectedRoutes.add(sl);
                selectedRoutesTableView.setItems(selectedRoutes);
            } else {
                lblWarning.setText("You have already selected this flight!");
            }

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
            if(selectedRoutes.get(i).getFlightID() == r.getFlightID()){
                selectedRoutes.remove(i);
                break;
            }
        }

        // Convert the selectedRoutes object back to a flight object
        Flight t = new Flight(r.getFlightID(), r.getPlane(), r.getArrivalDateTime(), r.getDepartureDateTime(), r.getPrice(), r.getArrivalAirport(), r.getDepartureAirport());
        // Add flight to the possible routes list
        foundRoutes.add(t);

    }

    private ObservableList<Flight> mockFlights(){
        Plane p1 = new Plane(1, 100, 100000);
        Airport berlin = new Airport(1, "Berlin");
        Airport nyc = new Airport(2, "New York");
        LocalDateTime f1da = LocalDateTime.now().plusDays(5); // Arrival
        LocalDateTime fl1dd = LocalDateTime.now().plusDays(4); // Departure
        Flight f1 = new Flight(1, p1, f1da, fl1dd, 10, berlin, nyc);

        Plane p2 = new Plane(2, 50, 5000);
        Airport amsterdam = new Airport(2, "Amsterdam");
        LocalDateTime f2da = LocalDateTime.now().plusDays(8); // Arrival
        LocalDateTime fl2dd = LocalDateTime.now().plusDays(7); // Departure
        Flight f2 = new Flight(2, p2, f2da, fl2dd, 100, amsterdam, nyc);

        ObservableList<Flight> allFlights = FXCollections.observableArrayList();

        allFlights.add(f1);
        allFlights.add(f2);
        allFlights.add(f1);

        return allFlights;

    }

}
