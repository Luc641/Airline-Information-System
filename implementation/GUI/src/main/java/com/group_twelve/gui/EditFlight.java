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
//define the GUI editFlight class
public class editFlight {

    // Define all of the FXML textfield / labels.
    @FXML
    private TextField txtFlightId;
    @FXML
    private TextField txtFlightPrice;
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

    public editFlight(Flight flight) {
        List<Object> flightData = getFlightData(flight);

        // Check if a value is empty.
        long emptyFields = flightData.stream()
                .filter(v -> v.equals(""))
                .count();

        // No empty fields, hurray! we can continue.
        if(emptyFields != 0){
	  //load the textfields with the values
          txtFlightPrice.setTxt(flight.getFlightPrice());
	  dpArrival.setValue(LOCAL_DATE(flight.getArrivalTime()));
	  dpDeparture.setValue(LOCAL_DATE(flight.getDepartureTime()));
	  txtArrivalAirport.setTxt(flight.getArrivalAirport());
	  txtDepartureAirport.setTxt(flight.getDepartureAirport());
	  txtPlaneId.setTxt(flight.getPlaneID());
	  txtRoute.setTxt(flight.getRoute());
	  txtCrewId.setTxt(flight.getCrewID());
	  txtOptionalRemarkt.setTxt(flight.getOptionalRemarkt());
        } else {
          
        }
        return false;
    }

    //this is the submit button click event, it changes all the flight entity values and then updates them in the database
    public void updateFlight(Flight flight) {
        //this function is so that the data from the textfields will be read, update the flight entity and then update the database
	flight.setFlightPrice(txtFlightPrice.getText());
	flight.setArrivalTime(dpArrival.getValue());
	flight.setDepartureTime(dpDeparture.getValue());
	flight.setArrivalAirport(txtArrivalAirport.getText());
	flight.setDepartureAirport(txtDepartureAirport.getText());
	flight.setPlaneID(txtPlaneId.getText());
	flight.setRoute(txtRoute.getText());
	flight.setCrewID(txtCrewId.getText());
	flight.setOptionalRemarkt(txtOptionalRemarkt.getText());

	//updates the values in the database
	flight.updateFlightDB();
    }

    @FXML
    public void cancelButton(ActionEvent e){
        System.exit(0);
    }

    /**
     * Get all of the necessary information from the GUI and insert them in a list.
     */
    @FXML
    public List<Object> getFlightData(Flight flight) {
        List<Object> returnList = new ArrayList<>();

        returnList.add(flight.getID());
	returnList.add(flight.getDepartureAirport());
	returnList.add(flight.getArrivalAirport());
	returnList.add(flight.getDepartureTime());
	returnList.add(flight.getArrivalTime());
	returnList.add(flight.getRoute());
	returnList.add(flight.getPlaneID());
	returnList.add(flight.getCrewID());
	returnList.add(flight.getOptionalRemarkt());

        System.out.println(returnList);

        return returnList;
    }

    private void back(ActionEvent event) throws IOException {
                GUIApp.setRoot("Homepage");

    }

    @FXML
    private void goBack(ActionEvent event) throws IOException {
                GUIApp.setRoot("Homepage");

    }
    }

