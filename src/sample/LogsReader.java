package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;


/**
 * Created by gabrysia on 10/16/16.
 */
public class LogsReader {
    @FXML
    TextArea logsPlace;
    public void showLogs(ActionEvent event){
        displayLogs();
    }
    public void displayLogs(){
        logsPlace.setText("allllllllllllllllllllllllllllllllllllllllllllllllllll\n" +
                "lllllllllllllllllllllllllllllllllllllllllllllllllllllllllllll" +
                ";;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;");


    }
}
