package sample.controllerimpl;

import database.daoimpl.UserDaoImpl;
import database.user.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sample.AlertBox;
import sample.controller.Controller;

import java.io.IOException;

/**
 * Created by runtitc on 10/25/16.
 */
public class SignUpController extends Controller{

    @FXML private Button registerCancel;
    @FXML private TextField registerLoginInput;
    @FXML private PasswordField registerPassInput;
    @FXML private Button registerInputButton;
    @FXML private PasswordField registerPassRepeatInput;

    public void onEventOccured(ActionEvent event) throws IOException {
        super.onEventOccured(event);

        //if the password and login are correct, then create the new entry in database
        if (id.equals(registerInputButton.getId())) {
            if (!loginInputValidation(registerLoginInput.getText()) ){

            }
            else if (!checkPasswordEqual(registerPassInput.getText(), registerPassRepeatInput.getText())) {
                AlertBox alert = new AlertBox("registrationErr", "Podane hasla roznia sie");
            }
            else if (!checkPasswordLength(registerPassInput.getText())) {
                AlertBox alert = new AlertBox("registrationErr", "Podane haslo musi byc dluzsze niz 8 znakow.");
            }
            else
            {
                UserDaoImpl user = new UserDaoImpl();
                user.createUser(new User(registerLoginInput.getText(), registerPassInput.getText()));
                AlertBox alert = new AlertBox("registrationSucc", "Mozesz teraz zalogowac sie do aplikacji.");
                switchScene(event, "../view/signin.fxml");
            }
        }
        if (id.equals(registerCancel.getId())){ switchScene(event, "../view/signin.fxml"); }

    }

    public boolean loginInputValidation(String username){

        if (checkUsernameLength(username)){
            AlertBox alert = new AlertBox("registrationErr", "Nazwa uzytkownika powinna miec <40 znakow");
            return false;
        }

        if (checkIfTheUsernameHasForbiddenCharacters(username)){
            //implement whitelist
            AlertBox alert = new AlertBox("registrationErr", "Dozwolone znaki: [a-zA-Z1-9]");
            return false;
        }

        if (checkIfTheUsernameAlreadyExists(username)){
            AlertBox alert = new AlertBox("registrationErr", "Podany uzytkownik juz istnieje");
            return false;
        }
        return true;
    }

    public boolean checkUsernameLength(String username){
        if (username.length() >= 40){
            return true;
        }
        else {
            return false;
        }
    }

    public boolean checkIfTheUsernameHasForbiddenCharacters(String username){

        String[] usernameSplitted = username.split("");

        for (int i=0; i<username.length(); i++ ) {
            boolean checkIfExists = false;
            for (int j = 48; j <= 57; j++) {
                if (usernameSplitted[i].equals(Character.toString((char) j))) {
                    checkIfExists = true;
                    break;
                }
            }
            if (!checkIfExists){
                for (int j = 65; j <= 90; j++) {
                    if (usernameSplitted[i].equals(Character.toString((char) j))) {
                        checkIfExists = true;
                        break;
                    }
                }
            }
            if (!checkIfExists) {
                for (int j = 97; j <= 122; j++) {
                    if (usernameSplitted[i].equals(Character.toString((char) j))) {
                        checkIfExists = true;
                        break;
                    }
                }
            }
            if (checkIfExists) return false;
        }
        return true;
   }

    public boolean checkIfTheUsernameAlreadyExists(String username){
        UserDaoImpl userToChecked = new UserDaoImpl();
        User usernameToChecked = userToChecked.selectByUsername(username);

        if (null == usernameToChecked.getUsername()){
            // user does not exist and it is OK, so return false
            return false;
        }else{
            //user does exist and it is NOT OK, so return truth
            return true;
        }
    }

    public boolean checkPasswordEqual(String pass1, String pass2){
        return (pass2.equals(pass1)) ? true: false;
    }

    public boolean checkPasswordLength(String pass1) {
        return (pass1.length()>=8)? true: false;
    }
}
