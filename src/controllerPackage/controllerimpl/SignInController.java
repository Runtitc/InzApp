package controllerPackage.controllerimpl;

import app.AlertBox;
import controllerPackage.controller.Controller;
import database.daoimpl.UserDaoImpl;
import database.objectDetails.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Created by runtitc on 10/25/16.
 */
public class SignInController extends Controller{

    @FXML private Button registerButton;
    @FXML private Button loginButton;

    //input data
    @FXML TextField loginUsername;
    @FXML PasswordField passwordUsername;

    public void onEventOccured(ActionEvent event) throws IOException {
        super.onEventOccured(event);

        if (id.equals(registerButton.getId())){ switchScene(event, "../../view/signup.fxml"); }
        if (id.equals(loginButton.getId().toString())){
            try {
                if (!loginUsername.getText().isEmpty() && !passwordUsername.getText().isEmpty()) {
                    if (validateUser(loginUsername.getText(), passwordUsername.getText())) {
                        switchScene(event, "../../view/mainWindow.fxml");
                        //tutaj jakos trzeba przeslac dane do MainWindow na temat logowania
                    }
                } else {
                    AlertBox alert = new AlertBox("loginErr", "Pola Login oraz Hasło nie mogą być puste!");
                }
            }catch (IOException e){
                e.printStackTrace();
            }
        }

    }

    public boolean validateUser(String loginUsername, String pass){
        //select the data from the db and insert it into the object Uuser'
        UserDaoImpl udi = new UserDaoImpl();
        User user = udi.selectByUsername(loginUsername);

        //if the User input the wrong credentials, then the proper alert will display.
        // Null Condition is necessary to mitigate the stacktrace's when comparing Surname to Null objects
        if (null != user.getUsername()){
            if (user.getUsername().equals(loginUsername)){
                if (user.getPassword().equals(pass)){
                    return true;
                } else {
                    AlertBox alert = new AlertBox("loginErr", "Prosze wprowadź dane poprawne.");
                }
            }else{
                AlertBox alert = new AlertBox("loginnErr", "Prosze wprowadź dane poprawne.");
            }
            return false;
        }else{
            AlertBox alert = new AlertBox("loginErr", "Proszę wprowadź dane poprawnie");
            return false;
        }
    }
}

