package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.IOException;

public class Controller{

    private String registerButton = "registerButton";
    private String registerCancel = "registerCancel";
    private String guestLogin = "guestLogInButton";

    public void onEventOccured(ActionEvent event) throws IOException {

        Button button = (Button) event.getSource();
        String id = button.getId();

        if (id.equals(registerButton)){ switchScene(event, "view/signup.fxml"); }
        if (id.equals(registerCancel)){ switchScene(event, "view/signin.fxml"); }
        if (id.equals(guestLogin)){ switchScene(event, "view/mainWindow.fxml"); }
    }

    public void switchScene(ActionEvent event, String fxmlResource) throws IOException {

        Parent registerPage = FXMLLoader.load(getClass().getResource(fxmlResource));
        Scene registerScene = new Scene(registerPage);

        Stage primaryStageRegister = (Stage) ((Node) event.getSource()).getScene().getWindow();

        primaryStageRegister.setScene(registerScene);
        primaryStageRegister.show();
    }
}
