package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

    private String logsPath = "/home/gabrysia/a.txt";
    private String logsContent = "";

    public void onEventOccured(ActionEvent event){

        if (event.getSource() instanceof Button) {
            //Button

            Button button = (Button) event.getSource();
            String buttonId = button.getId();

            if (buttonId.equals("showLogsButton")) {
                displayLogs();
            } else if (buttonId.equals("saveLogsButton")) {
                Stage primaryStage = getStage(event);
                saveLogsToFile(chooseFile(primaryStage));
            }
        }else if(event.getSource() instanceof MenuItem){
            //Menu Item

            MenuItem menuItem = (MenuItem) event.getSource();
            String menuItemId = menuItem.getId();

            if(menuItemId.equals("buttonProgramTermination")){
                System.out.println("Do widzenia!");
                System.exit(0);
            }
        }
        System.out.println(event.getSource());
    }
    public void saveLogsToFile(File file){

        PrintWriter saveFile = null;

        try {
            saveFile = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            AlertBox alert = new AlertBox("noFileFound", file.getName());
            return;
        }
        saveFile.write(logsContent);
        saveFile.close();
        AlertBox alert = new AlertBox("savedWithSuccess");

    }
    private Stage getStage(ActionEvent event){

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        return stage;
    }
    private File chooseFile(Stage stage){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(stage);

        return file;
    }
    private void displayLogs(){

        File logsFile = new File(logsPath);
        Scanner readable = null;
        try {
            readable = new Scanner(logsFile);
        } catch (FileNotFoundException e) {
            AlertBox alert = new AlertBox("noFileFound", logsFile.getName());
            return;
        }
        while (readable.hasNext()){
            String text = readable.nextLine();
            logsContent += text + "\n";
        }
        logsPlace.setText(logsContent);
    }

    public void menuBarOneventOccured(ActionEvent event) throws IOException{
        //Platform.exit();
        //MenuItem fileTermination = (MenuItem) event.getSource();
        //String id = fileTermination.getId();

        //if (id.equals(buttonFileTermination)) {}


    }
}
