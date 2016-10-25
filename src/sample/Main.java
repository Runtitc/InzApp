package sample;

import database.config.CreateConnection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;

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

        /*ExCmd sample = new ExCmd();
        System.out.println("Podaj haslo: ");
        //probowalem tutaj przeslac rzeczy do skryptu, ale tak czy siak, potem potrzeujemy modulu EXCEP, a do instalacji tego moduu potrzene jest haslo
        // Jedyne roziwiazanie jakie widzie na ten moment, to wyslanie prosto hasla do strumienia, gdzie prosza o haslo, a nastepnie przekierowanie polecenia dalej
        // kazde polecenie linijka po linijce powinno raczej zostac wykonywane komenda po komendzie
        Scanner reader = new Scanner(System.in);
        String n = reader.next(); // Scans the next token of the input as an int.
        System.out.println(n);

        System.out.println(sample.executeCommand("./snortInstall.sh", true));*/

        Connection conn = null;

        try{
            conn = CreateConnection.getConn();

            if (conn != null){
                System.out.println("Connection established!");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (conn != null){
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        launch(args);

    }
}
