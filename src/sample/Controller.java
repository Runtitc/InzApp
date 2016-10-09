package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{

    public void registerButtonOnClick(ActionEvent event) throws IOException {
        Parent registerPage = FXMLLoader.load(getClass().getResource("view/signup.fxml"));
        Scene registerScene = new Scene(registerPage);
        Stage primaryStageRegister = (Stage) ((Node) event.getSource()).getScene().getWindow();
        primaryStageRegister.setScene(registerScene);
        primaryStageRegister.show();
    }

}
