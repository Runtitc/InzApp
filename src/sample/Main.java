package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        checkSnortInstalled(primaryStage);

    }
    public void startApp(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/signin.fxml"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void checkSnortInstalled(Stage primaryStage) throws Exception {
        String appName = "snort";

        try {
            Process snortProgram = Runtime.getRuntime().exec(appName);
            snortProgram.destroy(); // now we destroy program cuz we will run it afterwards
            startApp(primaryStage);
        } catch (IOException e) {
            System.out.println("nie ma");
            AlertBox alert = new AlertBox("");

        }
    }
    public static void main(String[] args) {
        launch(args);

    }
}
