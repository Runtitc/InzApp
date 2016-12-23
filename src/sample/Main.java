package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        checkSnortRunning(primaryStage);
    }

    public void checkSnortRunning(Stage primaryStage) throws Exception {
        //Here we need to check whether Snort is running as a daemon.
        // To do tis we can use a bash command PGREP and see whether the process is already running.
        String appName = "snort";
        startApp(primaryStage);
    }

    public void startApp(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/signin.fxml"));
        primaryStage.setTitle("Snort Log Manager");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {

        launch(args);

    }
}
