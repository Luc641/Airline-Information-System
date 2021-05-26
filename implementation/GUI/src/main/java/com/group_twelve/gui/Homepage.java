package com.group_twelve.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

import javafx.scene.layout.AnchorPane;


import java.io.IOException;
import java.util.Objects;

public class Homepage {
    @FXML
    private Button priceReduction;
    @FXML
    private Button registerFlight;
    @FXML
    private Button createBooking;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private Button SearchFlight;


    @FXML
    private void loadRegisterFlight(ActionEvent event) throws IOException {
        GUIApp.setRoot("registerFlight");

    }

    @FXML
    private void loadCreateBooking(ActionEvent event) throws IOException {
        GUIApp.setRoot("CreateBookingMain");

    }

    @FXML
    private void loadSearchFlight(ActionEvent actionEvent) throws IOException {
        GUIApp.setRoot("searchFLight");
    }

    @FXML
    private void loadPriceReduction(ActionEvent actionEvent) throws IOException {
        GUIApp.setRoot("priceReductionNew");
    }
}
