package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;

import java.io.File;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


/**
 * Created by Przemix on 10/16/16.
 */
public class LogsReader {
    @FXML
    TextArea logsPlace;
    String logsDisplayer = "";


    public void showLogs(ActionEvent event){
        displayLogs();
    }
    public void saveLogsToFile(ActionEvent event){
        Parent registerPage = null;
        try {
            registerPage = FXMLLoader.load(getClass().getResource("view/mainWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene registerScene = new Scene(registerPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(stage);
        System.out.println(file.getAbsolutePath() + " file is " + file.getName());
        PrintWriter saveFile = null;
        try {
            saveFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        saveFile.write(logsDisplayer);
        saveFile.close();

    }
    public void displayLogs(){
        File logsFile = new File("/home/gabrysia/a.txt");
        Scanner readable = null;
        try {
            readable = new Scanner(logsFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (readable.hasNext()){
            String text = readable.nextLine();
            logsDisplayer += text + "\n";
        }
        logsPlace.setText(logsDisplayer);


    }
}
