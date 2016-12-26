package app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        enterServerAddress(primaryStage);
    }

    public void enterServerAddress(Stage primaryStage) throws Exception {

        while (true) {
            DialogPopUp serverAddressInput = new DialogPopUp();
            serverAddressInput.createDialog();

            if (DialogPopUp.validateConn(DialogPopUp.getServerAddr(), DialogPopUp.getDatabasePass())) {
                break;

            } else {
                new AlertBox("makingConnErr", "Niepoprawne dane");
            }
        }
        startApp(primaryStage);
    }

    public void startApp(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/signin.fxml"));
        primaryStage.setTitle("Snort Log Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }
}
