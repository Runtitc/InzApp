package sample.controllerimpl;

import database.daoimpl.UserDaoImpl;
import database.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.AlertBox;
import sample.controller.Controller;

import java.io.IOException;

/**
 * Created by runtitc on 10/25/16.
 */
public class SignUpController extends Controller{


    private String registerInputButton = "registerInputButton";
    @FXML private TextField registerLoginInput;
    @FXML private PasswordField registerPassInput;
    @FXML private PasswordField registerPassRepeatInput;
    private String registerCancel = "registerCancel";

    public void onEventOccured(ActionEvent event) throws IOException {
        super.onEventOccured(event);

        //i the password is correct, then create the new entry in database
        if (id.equals(registerInputButton)) {
            if (!checkPasswordEqual(registerPassInput.getText(), registerPassRepeatInput.getText())) {
                AlertBox alert = new AlertBox("registrationErr", "Podane hasla roznia sie");
            }
            else if (!checkPasswordLength(registerPassInput.getText())) {
                AlertBox alert = new AlertBox("registrationErr", "Podane haslo musi byc dluzsze niz 8 znakow.");
            }else
            {
                UserDaoImpl user = new UserDaoImpl();
                user.createUser(new User(registerLoginInput.getText(), registerPassInput.getText()));
                AlertBox alert = new AlertBox("registrationSucc", "Mozesz teraz zalogowac sie do aplikacji.");

            }
        }
        if (id.equals(registerCancel)){ switchScene(event, "../view/signin.fxml"); }

    }

    public boolean checkPasswordEqual(String pass1, String pass2){
        return (pass2.equals(pass1)) ? true: false;
    }

    public boolean checkPasswordLength(String pass1) {
        return (pass1.length()>=8)? true: false;
    }

}
