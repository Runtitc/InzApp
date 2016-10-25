package sample.controllerimpl;

import javafx.event.ActionEvent;
import sample.controller.Controller;

import java.io.IOException;

/**
 * Created by runtitc on 10/25/16.
 */
public class SignInController extends Controller{
    private String registerButton = "registerButton";
    private String guestLogin = "guestLogInButton";

    public void onEventOccured(ActionEvent event) throws IOException {
        super.onEventOccured(event);

        if (id.equals(registerButton)){ switchScene(event, "../view/signup.fxml"); }
        if (id.equals(guestLogin)){ switchScene(event, "../view/mainWindow.fxml"); }

    }
}

