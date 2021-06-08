package com.group_twelve.gui;

import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.entities.Flight;
import com.group_twelve.gui.searchable.Filter;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
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
        flightId.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getID()));
        departure.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        flightPrice.setCellValueFactory(new PropertyValueFactory<>("FlightPrice"));
        planeId.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getPlane().getID()));
        planeName.setCellValueFactory(s -> new SimpleObjectProperty<>(s.getValue().getPlane().getName()));


        // edit the columns
        flightTable.setEditable(true);
        flightTable.getSelectionModel().setCellSelectionEnabled(true);
        //flightId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //departure.setCellFactory(TextFieldTableCell.forTableColumn());
        //arrival.setCellFactory(TextFieldTableCell.forTableColumn());
        //departureTime.setCellFactory(TextFieldTableCell.forTableColumn(DateTimeConverter.getInstance()));
        //arrivalDate.setCellFactory(TextFieldTableCell.forTableColumn(DateTimeConverter.getInstance()));
        flightPrice.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        //planeId.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));

        flightPrice.setOnEditCommit(event -> {
            var selectedFlight = event.getRowValue();
            updatePriceForRow(event.getNewValue(), selectedFlight.getID());
        });

        //Set the table's items using the filtered list
        flightTable.setItems(data);

        //Adding ChoiceBox and TextField
        choiceBox.getItems().addAll("Flight Number", "Departure", "Arrival", "Departure Time", "Arrival Time", "Flight Price", "Plane Id", "Plane");
        choiceBox.setValue("Flight Number");

        //Adding the filters to the choiceBox and adding the db entries to an observable list
        textField.setPromptText("Search here!");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
                    Filter p = Filter.findFilter(choiceBox.getValue());
                    var f = observableArrayList(data.stream().filter(p.search(newValue)).collect(Collectors.toList()));
                    flightTable.setItems(f);
                }
        );

        choiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            //reset table and text field when new choice is selected
            if (newVal != null) {
                textField.setText("");
            }
        });
    }

    private void updatePriceForRow(int price, int flightId) {
        FlightManager flightManager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        flightManager.updatePriceById(flightId, price);
        lblInfo.setText("Price changed!");
    }

    //getting the flights into an observable list
    private ObservableList<Flight> getFlights() {
        FlightManager flightManager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        var x = flightManager.getAll();
        return observableList(x);
    }

    //deleting a row from the flight table and the db
    @FXML
    private void deleteRowFromTable(ActionEvent event) {
        var manager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        var x = flightTable.getSelectionModel().getSelectedItem();
        manager.delete(x.getID());
        flightTable.getItems().remove(x);
        lblInfo.setText("Flight deleted!");
    }

    //setting root links to direct to other pages
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
