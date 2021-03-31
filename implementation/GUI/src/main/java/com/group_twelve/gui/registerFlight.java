package com.group_twelve.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.util.ArrayList;
import java.util.List;

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
    private Button btnAccept;
    @FXML
    private Button btnCancel;


    @FXML
    public boolean storeFlight() {
        List<Object> flightData = getFlightData();

        // Check if a value is empty.
        long emptyFields = flightData.stream()
                .filter(v -> v.equals(""))
                .count();

        // No empty fields, hurray! we can continue.
        if(emptyFields != 0){

            // Check business logic stuff

        }
        return false;
    }

    //LUC/PATRICK/RICK
    @FXML
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

        // Check if optional remarks has been filled in, otherwise enter false.
        if(txtOptionalRemarkt.getText().equals("")){
            txtOptionalRemarkt.setText("false");
        }

        returnList.add(txtOptionalRemarkt.getText());

        System.out.println(returnList);

        return returnList;
    }
}
