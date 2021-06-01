package com.group_twelve.gui;
// imports
import com.group_twelve.businesslogic.FlightManager;
import com.group_twelve.entities.Flight;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
//define the GUI registerFlight class
public class registerFlight {

    // Define all of the FXML textfield / labels.
    @FXML
    private TextField txtFlightId;
    @FXML
    private TextField txtDepartureAirport;
    @FXML
    private TextField txtArrivalAirport;
    @FXML
    private DatePicker dpDeparture;
    @FXML
    private DatePicker dpArrival;
    @FXML
    private TextField txtRoute;
    @FXML
    private TextField txtPlaneId;
    @FXML
    private TextField txtCrewId;
    @FXML
    private TextArea txtOptionalRemarkt;
    @FXML
    private Button btnCancel;//
    @FXML
    private Button btnGoBack;
    @FXML
    private AnchorPane registerFlightPane;
    @FXML
    private Label registerFlights;
    @FXML
    private Button btnSubmit;


    public boolean storeFlight() {
        List<Object> flightData = getFlightData();

        // Check if a value is empty.
        long emptyFields = flightData.stream()
                .filter(v -> v.equals(""))
                .count();

        // No empty fields, hurray! we can continue.
        if(emptyFields != 0){
            Flight flight = new Flight();
        // new FlightManager().save(flight);
            // Check business logic stuff

//           airport manager -> retrieve list. if empty then no airplane

        } else {
          
        }
        return false;
    }

    //LUC/PATRICK/RICK
    public void storeInformation() {

    }

    @FXML
    public void cancelButton(ActionEvent e){
        System.exit(0);
    }

    /**
     * Get all of the necessary information from the GUI and insert them in a list.
     */
    @FXML
    public List<Object> getFlightData() {
        List<Object> returnList = new ArrayList<>();

        returnList.add(txtFlightId.getText());
        returnList.add(txtDepartureAirport.getText());
        returnList.add(txtArrivalAirport.getText());
        returnList.add(dpDeparture.getValue()); // LocalDate
        returnList.add(dpArrival.getValue()); // LocalDate
        returnList.add(txtRoute.getText());
        returnList.add(txtPlaneId.getText());
        returnList.add(txtCrewId.getText());

        // Check if optional remarks has been filled in, otherwise enter none.
        if(txtOptionalRemarkt.getText().equals("")){
            txtOptionalRemarkt.setText("none");
        }

        returnList.add(txtOptionalRemarkt.getText());

        System.out.println(returnList);

        return returnList;
    }

    private void back(ActionEvent event) throws IOException {
                GUIApp.setRoot("Homepage");

    }

    @FXML
    private void back(MouseEvent event) {
    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
                GUIApp.setRoot("Homepage");

    }
    }

