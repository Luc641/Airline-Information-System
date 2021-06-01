package com.group_twelve.gui;

import com.group_twelve.businesslogic.BusinessLogic;
import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.businesslogic.Manager;
import com.group_twelve.entities.Airport;
import com.group_twelve.entities.Flight;
import com.group_twelve.entities.Plane;
import com.group_twelve.gui.searchable.Filter;
import com.group_twelve.gui.searchable.Searchable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import static javafx.collections.FXCollections.observableArrayList;
import static javafx.collections.FXCollections.observableList;
import java.util.stream.Collectors;


public class searchFlight {
    @FXML
    private TableView<Flight> flightTable;
    @FXML
    private final ObservableList<Flight> data = getFlights();
    @FXML
    private TableColumn<Flight, Integer> flightId, departureTime, arrivalDate;
    @FXML
    private TableColumn<Flight, String> arrival, departure;
    @FXML
    private HBox hBox;
    @FXML
    ChoiceBox<String> choiceBox;
    @FXML
    TextField textField;

    @FXML
    public void initialize() {
        flightId.setCellValueFactory(new PropertyValueFactory<>("ID"));
        departure.setCellValueFactory(new PropertyValueFactory<>("departureAirport"));
        arrival.setCellValueFactory(new PropertyValueFactory<>("arrivalAirport"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("departureTime"));
        arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        flightTable.setEditable(true);

        FilteredList<Flight> flFlights = new FilteredList<>(data, p -> true); //passing data
        flightTable.setItems(flFlights); //Set the table's items using the filtered list
        //Adding ChoiceBox and TextField here!
        choiceBox.getItems().addAll("Flight Number", "Departure", "Arrival" , "Departure Time" , "Arrival Time");
        choiceBox.setValue("Flight Number");

        textField.setPromptText("Search here!");
        textField.textProperty().addListener((obs, oldValue, newValue) -> {
                    Filter p = Filter.findFilter(choiceBox.getValue());
                    var f = observableArrayList(flFlights.stream().filter(p.search(newValue)).collect(Collectors.toList()));
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

    private ObservableList<Flight> getFlights() {
        FlightManager flightManager = (FlightManager) GUIApp.getBusinessLogicAPI().getManager(Flight.class);
        var x = flightManager.getAll();
        return observableList(x);
    }

    @FXML
    private void  deleteRowFromTable(ActionEvent event){
        flightTable.getItems().removeAll(flightTable.getSelectionModel().getSelectedItem());
    }

}
