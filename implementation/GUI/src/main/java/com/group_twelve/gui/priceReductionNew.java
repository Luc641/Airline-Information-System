/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.group_twelve.gui;

import com.group_twelve.businesslogic.PriceReductionManager;
import com.group_twelve.entities.PriceReduction;
import com.group_twelve.entities.PriceReductionType;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.SelectionModel;

/**
 *
 * @author timo
 */
public class priceReductionNew {
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDescription;
    @FXML
    private ComboBox cbType;
    @FXML
    private TextField tfPercentage;
    @FXML
    private Button btnCreate;
    @FXML
    private Button btnGoBack;
    
    ObservableList observableList;
    
    @FXML
    private void initialize() {
        observableList = FXCollections.observableArrayList();
        PriceReductionType[] types = PriceReductionType.values();
        for(PriceReductionType type : types) {
            observableList.add(type.name());
        }
        cbType.setItems(observableList);
    }
    
    @FXML
    private void btnClickCreate() throws IOException {
        Object selectedItem = cbType.getSelectionModel().getSelectedItem();
        String name = tfName.getText();
        String description = tfDescription.getText();
        String percentageText = tfPercentage.getText();
        if(selectedItem == null || name.isEmpty() || description.isEmpty())
            return;
        PriceReductionManager prm = (PriceReductionManager) GUIApp.getBusinessLogicAPI().getManager(PriceReduction.class);
        double percentage;
        try{
            percentage = Double.valueOf(percentageText.replaceAll("%",""));
            if(percentage>100) {
                //error
            } else if(percentage>=1) {
                percentage = percentage / 100;
            }
            if(percentage < 0.01) {
                return;
            }
        } catch(Exception ex) {
            return;
        }
        
        prm.save(new String[]{name, description, String.valueOf(percentage), selectedItem.toString()});
        GUIApp.setRoot("priceReductionList");
    }
    
    @FXML
    private void btnClickGoBack() throws IOException {
        GUIApp.setRoot("priceReductionList");
        
    }
}
