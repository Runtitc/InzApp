package app;

import database.config.CreateConnection;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.util.Pair;

import java.sql.Connection;
import java.util.Optional;

/**
 * Created by runtitc on 23.12.16.
 */
public class DialogPopUp {
    private  static String serverAddr;
    private  static String databasePass;
    public boolean validation;
    public DialogPopUp(){}

    public void createDialog(){

        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Połącz z serwerem");
        dialog.setHeaderText("Wpisz proszę adres IP serwera oraz hasło do bazy.");

        //dialog.setGraphic(new ImageView((this.getClass().getResource("../icons/serverKey.png").toString())));

        ButtonType loginButtonT = new ButtonType("Login", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonT = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);

        dialog.getDialogPane().getButtonTypes().addAll(loginButtonT, cancelButtonT);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25,150,15,15));

        TextField serverAddress = new TextField();
        serverAddress.setPromptText("Adres IP serwera");
        PasswordField passServer = new PasswordField();
        passServer.setPromptText("Haslo do bazy");

        grid.add(new Label("Adres IP serwera: "),0 ,0);
        grid.add(serverAddress, 1, 0);
        grid.add(new Label("Hasło do bazy"), 0, 1);
        grid.add(passServer, 1, 1);
        dialog.getDialogPane().setContent(grid);

        dialog.setResultConverter(chosenButton -> {
            if (chosenButton== loginButtonT){
                return new Pair<>(serverAddress.getText(), passServer.getText()); //AUTOMATYZACJA
                //return new Pair<>("192.168.180.129", "snortpass");
            }
            if (chosenButton == cancelButtonT){
                System.exit(0);
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();

        result.ifPresent(serverData -> {
            serverAddr = serverData.getKey();
            databasePass = serverData.getValue();
        });
    }



    public static boolean validateConn(String serverAddr, String databasePass){
        Connection conn = null;
        try {
            conn = CreateConnection.getConn(getServerAddr(),getDatabasePass());
        }catch (Exception e){
            e.printStackTrace();
        }
        return null != conn;
    }



    public static String getServerAddr() {
        return serverAddr;
    }

    public static String getDatabasePass() {
        return databasePass;
    }
}
