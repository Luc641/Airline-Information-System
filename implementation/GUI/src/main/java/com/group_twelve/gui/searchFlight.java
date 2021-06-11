package com.group_twelve.gui;

import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.entities.Flight;
import com.group_twelve.gui.searchable.Filter;
import com.group_twelve.gui.utils.IntegerStringConv;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.HBox;
import javafx.util.converter.IntegerStringConverter;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.stream.Collectors;


public class searchFlight {
    @FXML
    public Label lblInfo;
    @FXML
    private TableView<Flight> flightTable;
    private final ObservableList<Flight> data = getFlights();
    @FXML
    private TableColumn<Flight, Integer> flightId, flightPrice, planeId;
    @FXML
    private TableColumn<Flight, LocalDateTime> departureTime, arrivalDate;
    @FXML
    private TableColumn<Flight, String> arrival, departure, planeName;
    @FXML
    private HBox hBox;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TextField textField;

    @FXML
    public void initialize() {
        /*
         *setting the values for the table columns
         */
        flightId.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getID()));
        departure.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getDepartureAirport().getName()));
        arrival.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getArrivalAirport().getName()));
        departureTime.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getDepartureTime()));
        arrivalDate.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getArrivalTime()));
        flightPrice.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getFlightPrice()));
        planeId.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getPlane().getID()));
        planeName.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getPlane().getName()));

        /*
         *edit the columns
         */
        flightTable.setEditable(true);
        flightTable.getSelectionModel().setCellSelectionEnabled(true);
        flightPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConv()));
        /*
         *Edit the flight price of the selected flight *
         */

        flightPrice.setOnEditCommit(event -> {
            var selectedFlight = event.getRowValue();
            var value = event.getNewValue();
            if (validateInputUpdate(value)) {
                updatePriceForRow(value, selectedFlight.getID());
            }else {
                lblInfo.setText("Wrong type of input");
            }
        });

        /*
         *Set the table's items using the filtered list
         */
        flightTable.setItems(data);

        /*
         *Adding ChoiceBox and TextField
         */
        choiceBox.getItems().addAll("Flight Number", "Departure", "Arrival", "Departure Time", "Arrival Time", "Flight Price", "Plane Id", "Plane");
        choiceBox.setValue("Flight Number");

        /*
         *Adding the filters to the choiceBox and adding the db entries to an observable list and displaying the filtered results
         */
        textField.setPromptText("Search here!");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
                    Filter p = Filter.findFilter(choiceBox.getValue());
                    updateFlightTable(p, newValue);
                }
        );

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            /*
            reset table and text field when new choice of filter is selected
             */
            if (newVal != null) {
                textField.setText("");
            }
        });
    }

    private boolean validateInputUpdate(int input) {
        return input > 0;
    }

    /*
    finds filter, searches, updates the table
     */
    private void updateFlightTable(Filter filter, String searchTerm) {
        var f = observableArrayList(data.stream().filter(filter.search(searchTerm)).collect(Collectors.toList()));
        flightTable.setItems(f);
    }

    // *getting the flights into an observable list
    private ObservableList<Flight> getFlights() {
        FlightManager flightManager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        var x = flightManager.getAll();
        return observableList(x);
    }

    // *updating the price for a certain db entry table row
    private void updatePriceForRow(int price, int flightId) {
        FlightManager flightManager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        flightManager.updatePriceById(flightId, price);
        lblInfo.setText("Price changed!");
    }

    /**
     * deleting a row from the flight table and the db
     *
     * @param event = if a row is selected and the delete button has been pressed the entry will be deleted
     **/
    @FXML
    private void deleteRowFromTable(ActionEvent event) {
        var manager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        var x = flightTable.getSelectionModel().getSelectedItem();
        manager.delete(x.getID());
        flightTable.getItems().remove(x);
        lblInfo.setText("Flight deleted!");
    }

    /*
    setting root links to direct to other pages
     */
    @FXML
    private void loadRegisterFlight(ActionEvent event) throws IOException {
        GUIApp.setRoot("registerFlight");
    }

    @FXML
    private void loadHomepage(ActionEvent event) throws IOException {
        GUIApp.setRoot("Homepage");
    }

    @FXML
    private void loadCreateBooking(ActionEvent event) throws IOException {
        GUIApp.setRoot("createBookingMain");
    }
}
