/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.gui;

import com.group_twelve.businesslogic.PriceReductionManager;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.Button;
import com.group_twelve.entities.PriceReduction;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;

/**
 *
 * @author timo
 */
public class priceReductionList {
    @FXML
    ListView lvPriceReductions;
    @FXML
    Button btnNew;
    @FXML
    Button btnGoBack;
    
    ObservableList observableList;
    
    @FXML
    public void initialize() {
        observableList = FXCollections.observableArrayList();
        PriceReductionManager prManager = (PriceReductionManager) GUIApp.getBusinessLogicAPI().getManager(PriceReduction.class);
        List<String> list = prManager.getAll().stream().map(pr -> (pr.getName())).collect(Collectors.toList());
        observableList.addAll(list);
        lvPriceReductions.setItems(observableList);
        lvPriceReductions.getSelectionModel().select(0);
    }
    
    @FXML
    private void btnClickNew() throws IOException {
        GUIApp.setRoot("priceReductionNew");
    }
    @FXML
    private void btnClickGoBack() throws IOException {
        GUIApp.setRoot("Homepage");
    }
    @FXML
    private void btnClickDelete() throws IOException {
        PriceReductionManager prm = (PriceReductionManager) GUIApp.getBusinessLogicAPI().getManager(PriceReduction.class);
        prm.delete(lvPriceReductions.getSelectionModel().getSelectedIndex()+1);
    }
    
    public ListView testOnly() {
        ListView lvTest = new ListView();
        observableList = FXCollections.observableArrayList();
        PriceReductionManager prManager = (PriceReductionManager) GUIApp.getBusinessLogicAPI().getManager(PriceReduction.class);
        List<String> list = prManager.getAll().stream().map(pr -> (pr.getName())).collect(Collectors.toList());
        observableList.addAll(list);
        lvTest.setItems(observableList);
        lvTest.getSelectionModel().select(0);
        return lvTest;
    }
}
