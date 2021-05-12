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
import java.util.List;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;

/**
 *
 * @author timo
 */
public class priceReductionList {
    @FXML
    private ListView lvPriceReductions;
    @FXML
    private Button btnEdit;
    @FXML
    private Button btnNew;
    @FXML
    private Button btnDeactivate;
    
    ObservableList observableList;
    
    @FXML
    private void initialize() {
        observableList = FXCollections.observableArrayList();
        PriceReductionManager prManager = (PriceReductionManager) GUIApp.getBusinessLogicAPI().getManager(PriceReduction.class);
        List<String> list = prManager.getAll().stream().map(pr -> (pr.getName())).collect(Collectors.toList());
        observableList.addAll(list);
        lvPriceReductions.setItems(observableList);
        lvPriceReductions.getSelectionModel().select(0);
    }
    
    @FXML
    private void btnClickNew() {
        
    }
    
    @FXML
    private void btnClickEdit() {
        
    }
    
    @FXML
    private void btnClickDeactivate() {
        
    }
}
