package controllerPackage.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{

    public String id;

    public void onEventOccured(ActionEvent event) throws IOException {

        Button button = (Button) event.getSource(); //kontroler szuka zrodla zdarzenia
        id = button.getId();

    }

    public void switchScene(ActionEvent event, String fxmlResource) throws IOException {
        //function to switch between windows
        Parent registerPage = FXMLLoader.load(getClass().getResource(fxmlResource));
        Scene registerScene = new Scene(registerPage);

        Stage primaryStageRegister = (Stage) ((Node) event.getSource()).getScene().getWindow();

        primaryStageRegister.setScene(registerScene);
        primaryStageRegister.show();

    }

    public void menuSwitchScene(MenuBar bar, String fxmlResource) throws IOException{
        Parent registerPage = FXMLLoader.load(getClass().getResource(fxmlResource));
        Scene registerScene = new Scene(registerPage);

        Stage primaryStageRegister = (Stage) bar.getScene().getWindow();

        primaryStageRegister.setScene(registerScene);
        primaryStageRegister.show();
    }
}
