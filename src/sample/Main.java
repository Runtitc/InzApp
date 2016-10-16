package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        checkSnortInstalled(primaryStage);

    }
    public void startApp(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("view/signin.fxml"));
        primaryStage.getIcons().add(new Image("file:images/icon.png"));
        primaryStage.setTitle("Welcome");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void checkSnortInstalled(Stage primaryStage) throws Exception {
        String appName = "snort";

        startApp(primaryStage);
        /*try {
            Process snortProgram = Runtime.getRuntime().exec(appName);
            snortProgram.destroy(); // now we destroy program cuz we will run it afterwards
            startApp(primaryStage);
        } catch (IOException e) {
            AlertBox alert = new AlertBox("");
            //sprawdzic czy nie powstaja procesy widmo
        }*/
    }
    public static void main(String[] args) {

        /*ExCmd sample = new ExCmd();
        System.out.println("Podaj haslo: ");
        //probowalem tutaj przeslac rzeczy do skryptu, ale tak czy siak, potem potrzeujemy modulu EXCEP, a do instalacji tego moduu potrzene jest haslo
        // Jedyne roziwiazanie jakie widzie na ten moment, to wyslanie prosto hasla do strumienia, gdzie prosza o haslo, a nastepnie przekierowanie polecenia dalej
        // kazde polecenie linijka po linijce powinno raczej zostac wykonywane komenda po komendzie
        Scanner reader = new Scanner(System.in);
        String n = reader.next(); // Scans the next token of the input as an int.
        System.out.println(n);

        System.out.println(sample.executeCommand("./snortInstall.sh", true));*/
        launch(args);

    }
}
