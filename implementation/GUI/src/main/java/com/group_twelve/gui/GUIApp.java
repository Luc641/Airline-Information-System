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

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("registerFlight"));

        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GUIApp.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    

    public static void main(String[] args) {
        launch();
    }
    
    public void startFrontEnd(BusinessLogic api){
        businessLogicAPI = api;
        launch();
    }

}