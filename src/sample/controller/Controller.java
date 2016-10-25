package sample.controller;

import database.daoimpl.UserDaoImpl;
import database.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Controller{

    private String registerButton = "registerButton";
    private String registerCancel = "registerCancel";
    private String guestLogin = "guestLogInButton";

    private String registerInputButton = "registerInputButton";
    private String registerLoginInput = "registerLoginInput";
    private String registerPassInput = "registerPassInput";
    private String registerPassRepeatInput = "registerPassRepeatInput";
    public void onEventOccured(ActionEvent event) throws IOException {

        Button button = (Button) event.getSource(); //kontroler szuka zrodla zdarzenia
        String id = button.getId();

        if (id.equals(registerButton)){ switchScene(event, "../view/signup.fxml"); }
        if (id.equals(registerCancel)){ switchScene(event, "../view/signin.fxml"); }
        if (id.equals(guestLogin)){ switchScene(event, "../view/mainWindow.fxml"); }

        //i the password is correct, then create the new entry in database
        if (id.equals(registerInputButton)) {
            if (checkPassword(registerPassInput, registerPassRepeatInput)) {
                UserDaoImpl user = new UserDaoImpl()
                user.createUser(new User(registerLoginInput, registerPassInput));
            }else {
                System.out.println("Password Incorrect");
            }
        }
    }

    public void switchScene(ActionEvent event, String fxmlResource) throws IOException {

        //funkcja do przechodzenia pomiedzy okienkami, najpierw ladowane sa do zmiennej resterPage obecna strona fxml

        Parent registerPage = FXMLLoader.load(getClass().getResource(fxmlResource));
        Scene registerScene = new Scene(registerPage);

        Stage primaryStageRegister = (Stage) ((Node) event.getSource()).getScene().getWindow();

        primaryStageRegister.setScene(registerScene);
        primaryStageRegister.show();

    }

    public boolean checkPassword(String pass1, String pass2){
        if (pass1 == pass2){
            return true;
        }else{
            return false;
        }
    }
}
