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
    @FXML
    private TextField txtNrOfTickets;

    private int tCount;

    private ObservableList<Flight> foundRoutes = FXCollections.observableArrayList();
    private ObservableList<selectedRoutes> selectedRoutes = FXCollections.observableArrayList();

    AirportManager apm;
    RouteManager rm;
    FlightManager fm;

    /**
     * Initialize the double mouseclick events and the table columns.
     */
    @FXML
    private void initialize(){

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
    private void searchFlightRoutes(){

        // Empty warning label
        lblWarning.setText("");
        // Empty the previously found flightRoutes
        foundRoutes = FXCollections.observableArrayList();

        // Get values from the UI and validate them.
        ArrayList<String> validatedInput = validateInput(txtArrivalAirport.getText(), txtDepatureAirport.getText(), txtNrOfTickets.getText());

        // Check if the arraylist is empty. If true, display error and cancel the lookup.
        if(validatedInput.isEmpty()){
            lblWarning.setText("The inserted input isn't correct!");
            txtArrivalAirport.setText("");
            txtDepatureAirport.setText("");
            txtNrOfTickets.setText("");
        }else {

            String depAirport = validatedInput.get(0);
            String arAirport = validatedInput.get(1);
            this.tCount = Integer.parseInt(validatedInput.get(2));

            /**
             * temp override so that I don't have to enter names continiously + the flights are temporarily mocked anyways :)
             */
//            depAirport = "Berlin";
//            arAirport = "New York";

            // Get flight route(s) that have matching airports
            List<Route> routes = rm.getRouteBasesOnAirports(apm.getAirportId(depAirport), apm.getAirportId(arAirport));
            List<Integer> flightIds = routes.stream().map(Route::getFlightID).collect(Collectors.toList());

            // Collect all flights and add them to the foundRoutes list.
            flightIds.stream().forEach(id -> foundRoutes.add(fm.getFlightById(id)));
            tViewPossibleRoutes.setItems(foundRoutes);

            // Check if the table is empty. If so, display error message.
            if(foundRoutes.isEmpty()){
                lblWarning.setText("No flights could be found with these airports.");
            }
        }
    }

    /**
     * Remove the double-clicked flight from the "possible flights" table into the "selected flights" table.
     * Furthermore, the selected flight will be added into the selectedRoutes observableList.
     * @param f = the selected flight
     */
    @FXML
    private void selectRoute(Flight f) {
        // Reset the warningLabel in case that there is a warning active.
        lblWarning.setText("");

        // If one or more flights have already been selected:
        // Check:
        // - if arrival and departure times overlap
        if (selectedRoutes.size() > 0 && fm.checkForFlightOverlap(selectedRoutes.get(selectedRoutes.size() - 1), f)) {
            // If overlap, show error and cancel the selection of the route.
            lblWarning.setText("ERROR: The departure and arrival times overlap!");
        }else{
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
                selectedRoutes sl = new selectedRoutes(f.getID(), f.getPlane(), f.getArrivalAirport(), f.getDepartureAirport(), f.getDepartureTime(), f.getArrivalTime(), f.getFlightPrice(), this.tCount);

                // Add to the selected routes tableview
                selectedRoutes.add(sl);
                selectedRoutesTableView.setItems(selectedRoutes);

                // Empty the airport textfields for easy-of-use
                txtDepatureAirport.setText("");
                txtArrivalAirport.setText("");

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
    private void removeSelectedRoute(selectedRoutes r){

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

    /**
     * Validates the input given by the user and uppercases the first letter of the a1 and a2 string.
     * @param a1 = Departure Airport
     * @param a2 = Arrival Airport
     * @param tCount = Count of the number of tickets.
     * @return true or false
     */
    private ArrayList<String> validateInput(String a1, String a2, String tCount){

        // 1: Validate that both a1 and a2 only contain letters and that tCount only contains number(s). Also check that the strings arent empty.
        if(!a1.matches("[a-zA-Z]+") || !a2.matches("[a-zA-Z]+") || !tCount.matches("\\d+") || a1.equals("") || a2.equals("") || tCount.equals("")){
            return new ArrayList<String>();
        }else{
            // 3: Uppercase the first letter of both a1 and a2 so that it conforms with the style of the DB.
            String ua1 = a1.substring(0, 1).toUpperCase() + a1.substring(1);
            String ua2 = a2.substring(0, 1).toUpperCase() + a2.substring(1);

            // Send back the new data
            ArrayList<String> t = new ArrayList<>();
            t.add(ua1);
            t.add(ua2);
            t.add(tCount);

            System.out.println(tCount.matches("\\d+"));

            return t;
        }
    }

    private void saveBooking(){
        // TODO: Push the selectedRoutes table into the database.
    }

    /**
     * Temporary method which mocks flights to show in the tableview. This will be deleted later once the functionality
     * has been implemented into the managers.
     * @return list of mocked flights.
     */
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
