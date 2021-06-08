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

    CreateBookingImpl businessLogic;

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

        // Init the implementation
        businessLogic = new CreateBookingImpl(apm, rm, fm, bm, cm, tm, om);

        // init the tableviews.
        initTableViews();

        correctTableviews();

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

        try{
            businessLogic.searchFlightRoutes(txtArrivalAirport.getText(), txtDepatureAirport.getText(), txtNrOfTickets.getText());
            correctTableviews();
            showSuccessMessage("Updated the found flights!");
        }catch(Exception e){
            lblWarning.setText(e.getMessage());
        }

    }

    /**
     * Remove the double-clicked flight from the "possible flights" table into the "selected flights" table.
     * Furthermore, the selected flight will be added into the selectedRoutes observableList.
     * @param f = the selected flight
     */
    @FXML
    private void selectRoute(Flight f) {

        try{
            businessLogic.selectRoute(f);
            correctTableviews();
            showSuccessMessage("Flight selected!");
        }catch (Exception e){
            lblWarning.setText(e.getMessage());
        }

    }

    /**
     * This method removes a route from the selected routes table, removes it from the selected routes observable list
     * and puts it back into the foundRoutes arraylist.
     * @param r
     */
    private void removeSelectedRoute(selectedRoutes r){

        try{
            businessLogic.removeSelectedRoute(r);
            correctTableviews();
            showSuccessMessage("Flight unselected!");
        }catch (Exception e){
            lblWarning.setText(e.getMessage());
        }

    }

    /**
     * This method saves all of the selected routes as individual bookings.
     * It then grabs the tickets and pushes those into the ticket table, coupled to the booking itself.
     *
     * When done, display a success message and then empty all of the tables and textfields.
     */
    @FXML
    private void saveBooking(){
        try{
            businessLogic.saveBooking(txtCustomerName.getText());
            resetUI();
            showSuccessMessage("Booking has been saved!");
        }catch (Exception e){
            lblWarning.setText(e.getMessage());
        }
    }

    /**
     * Move an option from the found to the selected lists and tableview.
     * @param option
     */
    private void selectOption(Option option){
        try{
            businessLogic.selectOption(option);
            correctTableviews();
            showSuccessMessage("Option selected!");
        }catch (Exception e){
            lblWarning.setText(e.getMessage());
        }

    }

    /**
     * Move an option from the selected to the found list and tableview.
     * @param option
     */
    private void removeSelectedOption(Option option){
        try{
            businessLogic.removeSelectedOption(option);
            correctTableviews();
            showSuccessMessage("Option deselected!");
        }catch (Exception e){
            lblWarning.setText(e.getMessage());
        }
    }

    /**
     * Reset the UI and lists.
     */
    private void resetUI(){
        txtDepatureAirport.setText("");
        txtArrivalAirport.setText("");
        txtNrOfTickets.setText("");
        txtCustomerName.setText("");

        for (int i = 0; i < selectedRoutesList.size(); i++) {
            selectedRoutesList.remove(i);
        }
        for (int i = 0; i < foundRoutes.size(); i++) {
            foundRoutes.remove(i);
        }
    }

    private void correctTableviews(){
        foundRoutes.setAll(businessLogic.getFoundRoutes());
        selectedRoutesList.setAll(businessLogic.getSelectedRoutesList());
        selectedOptions.setAll(businessLogic.getSelectedOptions());
        foundOptions.setAll(businessLogic.getFoundOptions());

        selectedRoutesTableView.setItems(selectedRoutesList);
        tViewPossibleRoutes.setItems(foundRoutes);
        tViewSelectedOptions.setItems(selectedOptions);
        tViewOptions.setItems(foundOptions);

        lblTotalPrice.setText(String.valueOf(businessLogic.getTotalPrice()));

    }

    public void showSuccessMessage(String msg){
        lblWarning.setTextFill(Color.rgb(0, 128, 0));
        lblWarning.setText(msg);
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
