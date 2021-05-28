package com.group_twelve.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import com.group_twelve.businesslogic.BusinessLogic;

/**
 * JavaFX App
 * 
 * @author Timo Mattern (t.mattern@student.fontys.nl, github: @t-mattern), Fontys Venlo
 */
public class GUIApp extends Application {

    private static Scene scene;
    private static BusinessLogic businessLogicAPI;

    // Controller definitions
    private static registerFlight rf = new registerFlight();
    private static priceReductionList pRL = new priceReductionList();
    private static createBookingMain cbm = new createBookingMain();

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Homepage"));

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    protected static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApp.class.getResource(fxml + ".fxml"));
        System.out.println(fxml);
        // Set the controller depending on which view we load / which controller we need.
        //fxmlLoader.setController(homepage);
        return fxmlLoader.load();
    }
    

    public static void main(String[] args) {
        launch();
    }
    
    public static void startFrontEnd(BusinessLogic api){
        businessLogicAPI = api;
        launch();
    }
    
    public static BusinessLogic getBusinessLogicAPI() {
        return businessLogicAPI;
    }

}