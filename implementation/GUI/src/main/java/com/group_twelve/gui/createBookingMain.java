package com.group_twelve.gui;

import com.group_twelve.businesslogic.*;
import com.group_twelve.entities.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.lang.reflect.Array;
import java.time.LocalDate;
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
    private TextField txtCustomerName;
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
    @FXML
    private TableView<Option> tViewOptions;
    @FXML
    private TableColumn<Option, String> tColOptionsName;
    @FXML
    private TableColumn<Option, Integer> tColOptionsPrice;
    @FXML
    private TableView<Option> tViewSelectedOptions;
    @FXML
    private TableColumn<Option, String> tColSelectedOptionName;
    @FXML
    private TableColumn<Option, Integer> tColSelectedOptionPrice;
    @FXML
    private Label lblTotalPrice;

    private int tCount;

    private ObservableList<Flight> foundRoutes = FXCollections.observableArrayList();
    private ObservableList<selectedRoutes> selectedRoutesList = FXCollections.observableArrayList();
    private ObservableList<Option> foundOptions = FXCollections.observableArrayList();
    private ObservableList<Option> selectedOptions = FXCollections.observableArrayList();

    AirportManager apm;
    RouteManager rm;
    FlightManager fm;
    BookingManager bm;
    CustomerManager cm;
    TicketManager tm;
    OptionManager om;

    /**
     * Initialize the double mouseclick events and the table columns.
     */
    @FXML
    private void initialize(){

        // Init managers
        apm = (AirportManager) GUIApp.getBusinessLogicAPI().getManager(Airport.class);
        rm = (RouteManager) GUIApp.getBusinessLogicAPI().getManager(Route.class);
        fm = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        bm = (BookingManager) GUIApp.getBusinessLogicAPI().getManager(Booking.class);
        cm = (CustomerManager) GUIApp.getBusinessLogicAPI().getManager(Customer.class);
        tm = (TicketManager) GUIApp.getBusinessLogicAPI().getManager(Ticket.class);
        om = (OptionManager) GUIApp.getBusinessLogicAPI().getManager(Option.class);


        // init the tableviews.
        initTableViews();

        // Get all of the options and populate the options tableview
        foundOptions.addAll(om.getAll());
        tViewOptions.setItems(foundOptions);

    }

    public void initTableViews(){
        // Enable the double-mouseclick functionality for the tables.
        tViewOptions.setRowFactory(tv -> {
            TableRow<Option> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (! row.isEmpty())){
                    // Send data to the select flight method.
                    selectOption(row.getItem());
                }
            });
            return row;
        });

        tViewSelectedOptions.setRowFactory(tv -> {
            TableRow<Option> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if(event.getClickCount() == 2 && (! row.isEmpty())){
                    // Send data to the select flight method.
                    removeSelectedOption(row.getItem());
                }
            });
            return row;
        });

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


        // Init the columns
        tColOptionsName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColOptionsPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

        tColSelectedOptionName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tColSelectedOptionPrice.setCellValueFactory(new PropertyValueFactory<>("price"));

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
        ArrayList<String> validatedInput = bm.validateInput(txtArrivalAirport.getText(), txtDepatureAirport.getText(), txtNrOfTickets.getText());

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
        if (selectedRoutesList.size() > 0 && fm.checkForFlightOverlap(selectedRoutesList.get(selectedRoutesList.size() - 1), f)) {
            // If overlap, show error and cancel the selection of the route.
            lblWarning.setText("ERROR: The departure and arrival times overlap!");
        }else{
            // Check if the flight hasn't been inserted into the list already.
            long matchingFlights = selectedRoutesList.stream()
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
                selectedRoutesList.add(sl);
                selectedRoutesTableView.setItems(selectedRoutesList);

                // Set the departure airport textfield to the previous arrival airport and empty the arrival field for ease-of-use
                txtDepatureAirport.setText(sl.getArrivalAirportName());
                txtArrivalAirport.setText("");

                // Update total price
                updatePrice();

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
        for (int i = 0; i < selectedRoutesList.size(); i++) {
            if(selectedRoutesList.get(i).getFlightID() == r.getFlightID()){
                selectedRoutesList.remove(i);
                break;
            }
        }

        // Convert the selectedRoutes object back to a flight object
        Flight t = new Flight(r.getFlightID(), r.getPlane(), r.getArrivalDateTime(), r.getDepartureDateTime(), r.getPrice(), r.getArrivalAirport(), r.getDepartureAirport());
        // Add flight to the possible routes list
        foundRoutes.add(t);

        // Update total price
        updatePrice();

    }

    /**
     * This method saves all of the selected routes as individual bookings.
     * It then grabs the tickets and pushes those into the ticket table, coupled to the booking itself.
     *
     * When done, display a success message and then empty all of the tables and textfields.
     */
    @FXML
    private void saveBooking(){
        // Prepare variable
        boolean success = true;

        // Get customer details
        if(txtCustomerName.getText().equals("")) {
            lblWarning.setText("Please insert customer name");
        }else {
            Customer customer = cm.getOrCreateCustomerByName(cm.capitalizeName(txtCustomerName.getText()));
            if (customer.getID() == -1) {
                lblWarning.setText("Error with creating / fetching customer");
            } else {
                // Loop through selected flights and create the bookings
                for (int i = 0; i < selectedRoutesList.size(); i++) {
                    selectedRoutes sl = selectedRoutesList.get(i);

                    // Create tickets (ID = -1 because it isnt used for the insertion and we dont have it yet at this stage)
                    List<Integer> optionIds = selectedOptions.stream().map(Option::getID).collect(Collectors.toList());
                    for (int j = 0; j < tCount; j++) {
                        Ticket ticket = new Ticket(-1, -1, sl.getFlightID(), optionIds, customer.getID());
                        tm.save(ticket);
                    }

                    // Create and save the booking
                    Booking b = new Booking(LocalDate.now(), sl.getFlightID(), 1, 1);
                    success = bm.save(b);
                    if (!success) {
                        System.out.println("break");
                        break;
                    }
                }

                // If the success boolean is false, display warning.
                if (!success) {
                    lblWarning.setText("Error whilst saving booking.");
                } else {
                    // Booking created! display green message and clear all the fields.
                    lblWarning.setTextFill(Color.rgb(0, 128, 0));
                    lblWarning.setText("Booking created!");
                    resetUI();
                }
            }
        }

    }

    /**
     * Move an option from the found to the selected lists and tableview.
     * @param option
     */
    private void selectOption(Option option){
        // Reset the warningLabel in case that there is a warning active.
        lblWarning.setText("");

        // Check whether or not the option has been selected already
        Long matching = foundOptions.stream().map(v -> v.getID()).count();

        if(matching == 0){
            lblWarning.setText("Option already selected!");
        }else{
            // Remove flight from the found options table
            for (int i = 0; i < foundOptions.size(); i++) {
                if(foundOptions.get(i).getID() == option.getID()){
                    foundOptions.remove(i);
                    break;
                }
            }
            // Add the option to the selected options tableview & list
            selectedOptions.add(option);
            tViewSelectedOptions.setItems(selectedOptions);

            // Update total price
            updatePrice();
        }

    }

    /**
     * Move an option from the selected to the found list and tableview.
     * @param option
     */
    private void removeSelectedOption(Option option){
        // Remove from selected list
        for (int i = 0; i < selectedOptions.size(); i++) {
            if(selectedOptions.get(i).getID() == option.getID()){
                selectedOptions.remove(i);
                break;
            }
        }

        // Add to found list
        foundOptions.add(option);

        // Update total price
        updatePrice();

    }

    /**
     * Update the total price of the booking
     */
    private void updatePrice(){
        int newPrice = 0;

        // Get all the prices from the selectedRoutes.
        newPrice += selectedRoutesList.stream()
                .map(selectedRoutes::getPrice).mapToInt(v -> v).sum();

        // Same for the selected options
        newPrice += selectedOptions.stream()
                .map(Option::getPrice).mapToInt(v -> v).sum();

        // Multiply the cost by amount of tickets.
        if(tCount <= 1) {
            newPrice = newPrice * 1;
        }else{
            newPrice = newPrice * tCount;
        }
        // Display the new price.
        lblTotalPrice.setText(String.valueOf(newPrice));

    }

    /**
     * Reset the UI and lists.
     */
    private void resetUI(){
        txtDepatureAirport.setText("");
        txtArrivalAirport.setText("");
        txtNrOfTickets.setText("");

        for (int i = 0; i < selectedRoutesList.size(); i++) {
            selectedRoutesList.remove(i);
        }
        for (int i = 0; i < foundRoutes.size(); i++) {
            foundRoutes.remove(i);
        }
    }

    @FXML
    public void goBack() throws IOException {
        GUIApp.setRoot("Homepage");
    }

    @FXML
    public void cancel() throws IOException {
        GUIApp.setRoot("createBookingMain");
    }

}
